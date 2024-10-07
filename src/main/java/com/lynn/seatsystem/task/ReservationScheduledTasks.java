package com.lynn.seatsystem.task;

import com.lynn.seatsystem.constant.SeatConstant;
import com.lynn.seatsystem.domain.entity.Reservation;
import com.lynn.seatsystem.domain.entity.Seat;
import com.lynn.seatsystem.mapper.ReservationMapper;
import com.lynn.seatsystem.mapper.SeatMapper;
import com.mybatisflex.core.query.QueryChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.lynn.seatsystem.domain.entity.table.ReservationTableDef.RESERVATION;

/**
 * Author: Lynn
 * Date: 2024/10/07 下午12:24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationScheduledTasks {
    private final ReservationMapper reservationMapper;
    private final SeatMapper seatMapper;

    /**
     * 每一个小时执行一次，释放未确认的座位。
     */
    @Scheduled(fixedRate = 3600000)
    public void releaseExpiredReservation() {
        List<Reservation> expired = QueryChain.of(reservationMapper)
                .select()
                .from(RESERVATION)
                .where(RESERVATION.STATUS.eq(SeatConstant.RESERVATION_STATUS_PENDING))
                .and(RESERVATION.END_TIME.lt(LocalDateTime.now()))
                .listAs(Reservation.class);

        for (Reservation reservation : expired) {
            Seat locked = seatMapper.lockSeat(reservation.getSeatId());
            reservation.setStatus(SeatConstant.RESERVATION_STATUS_CANCELLED);
            reservation.setUpdateTime(LocalDateTime.now());
            reservationMapper.update(reservation, true);
            log.info("释放未确认的座位，释放信息：{}", reservation);

            locked.setSeatId(reservation.getSeatId());
            locked.setSeatStatus(SeatConstant.SEAT_STATUS_AVAILABLE);
            locked.setUpdateTime(LocalDateTime.now());
            seatMapper.update(locked, true);
            log.info("释放座位，座位信息：{}", locked);
        }
    }

    /**
     * 每一个小时执行一次，释放到期的座位。
     */
    @Scheduled(fixedRate = 3600000)
    public void releaseExpiredSeat() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.minusHours(SeatConstant.SEAT_OCCUPIED_TIMEOUT_HOUR);

        List<Reservation> expiredReservations = QueryChain.of(reservationMapper)
                .select()
                .from(RESERVATION)
                .where(RESERVATION.STATUS.eq(SeatConstant.RESERVATION_STATUS_CONFIRMED))
                .and(RESERVATION.START_TIME.le(expirationTime))
                .listAs(Reservation.class);

        for (Reservation reservation : expiredReservations) {
            Seat locked = seatMapper.lockSeat(reservation.getSeatId());

            reservation.setStatus(SeatConstant.RESERVATION_STATUS_COMPLETED);
            reservation.setUpdateTime(LocalDateTime.now());
            reservationMapper.update(reservation, true);
            log.info("释放过期的座位，释放信息：{}", reservation);

            locked.setSeatStatus(SeatConstant.SEAT_STATUS_AVAILABLE);
            locked.setUpdateTime(LocalDateTime.now());
            seatMapper.update(locked, true);
            log.info("释放座位，座位信息：{}", locked);
        }
    }

}
