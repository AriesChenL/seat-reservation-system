package com.lynn.seatsystem.service.impl;

import com.lynn.seatsystem.constant.SeatConstant;
import com.lynn.seatsystem.domain.entity.Seat;
import com.lynn.seatsystem.exception.BusinessException;
import com.lynn.seatsystem.mapper.SeatMapper;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lynn.seatsystem.domain.entity.Reservation;
import com.lynn.seatsystem.mapper.ReservationMapper;
import com.lynn.seatsystem.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.lynn.seatsystem.domain.entity.table.ReservationTableDef.RESERVATION;


/**
 * 预约表 服务层实现。
 *
 * @author root
 * @since 2024-10-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements ReservationService {
    private final SeatMapper seatMapper;
    private final ReservationMapper reservationMapper;

    @Override
    public void createReservation(long seatId, long userId) {
        // 查询是否已经预约过座位
        List<Reservation> reservations = QueryChain.of(reservationMapper)
                .select()
                .from(RESERVATION)
                .where(RESERVATION.USER_ID.eq(userId))
                .and(RESERVATION.STATUS.eq(SeatConstant.RESERVATION_STATUS_PENDING))
                .listAs(Reservation.class);
        if (!reservations.isEmpty()) {
            throw new BusinessException("您已预约过座位，请勿重复预约");
        }

        // 使用悲观锁锁定座位记录
        Seat lock = seatMapper.lockSeat(seatId);
        if (lock == null || !SeatConstant.SEAT_STATUS_AVAILABLE.equalsIgnoreCase(lock.getSeatStatus())) {
            throw new BusinessException("座位不可被预约");
        }

        // 更新座位状态
        lock.setSeatStatus(SeatConstant.SEAT_STATUS_RESERVED);
        lock.setUpdateTime(LocalDateTime.now());
        seatMapper.update(lock);

        // 创建预约
        LocalDateTime now = LocalDateTime.now();
        Reservation reservation = Reservation.builder()
                .seatId(seatId)
                .userId(userId)
                .startTime(now)
                .endTime(now.plusHours(12))
                .status(SeatConstant.RESERVATION_STATUS_PENDING)
                .build();

        boolean flag = this.save(reservation);
        if (flag) {
            log.info("预约成功，预约信息：{}", reservation);
        } else {
            throw new BusinessException("预约失败");
        }
    }

    @Override
    public void cancelReservation(long reservationId, long userId) {
        Reservation reservation = QueryChain.of(reservationMapper)
                .select()
                .from(RESERVATION)
                .where(RESERVATION.RESERVATION_ID.eq(reservationId))
                .and(RESERVATION.USER_ID.eq(userId))
                .and(RESERVATION.STATUS.eq(SeatConstant.RESERVATION_STATUS_PENDING))
                .oneAs(Reservation.class);
        if (reservation == null) {
            throw new BusinessException("预约不存在或已取消");
        }

        // 锁定座位信息
        Seat seat = seatMapper.lockSeat(reservation.getSeatId());
        reservation.setStatus(SeatConstant.RESERVATION_STATUS_CANCELLED);
        reservation.setUpdateTime(LocalDateTime.now());
        reservationMapper.update(reservation, true);

        seat.setUpdateTime(LocalDateTime.now());
        seat.setSeatStatus(SeatConstant.SEAT_STATUS_AVAILABLE);
        seatMapper.update(seat, true);
    }

    @Override
    public void confirmReservation(long reservationId, long userId) {
        // 查询是否已经预约过座位
        List<Reservation> reservations = QueryChain.of(reservationMapper)
                .select()
                .from(RESERVATION)
                .where(RESERVATION.USER_ID.eq(userId))
                .and(RESERVATION.STATUS.eq(SeatConstant.RESERVATION_STATUS_PENDING))
                .listAs(Reservation.class);
        if (reservations.isEmpty()) {
            throw new BusinessException("您还未预约座位，无法确认预约");
        }

        Reservation reservation = reservations.getFirst();

        // 锁定座位
        Seat seat = seatMapper.lockSeat(reservation.getSeatId());
        reservation.setStatus(SeatConstant.RESERVATION_STATUS_CONFIRMED);
        reservation.setUpdateTime(LocalDateTime.now());
        reservationMapper.update(reservation, true);

        seat.setSeatStatus(SeatConstant.SEAT_STATUS_OCCUPIED);
        seat.setUpdateTime(LocalDateTime.now());
        seatMapper.update(seat, true);
    }

    @Override
    public void leaveSeat(long reservationId, long userId) {
        Reservation reservation = QueryChain.of(reservationMapper)
                .select()
                .from(RESERVATION)
                .where(RESERVATION.RESERVATION_ID.eq(reservationId))
                .and(RESERVATION.USER_ID.eq(userId))
                .and(RESERVATION.STATUS.eq(SeatConstant.RESERVATION_STATUS_CONFIRMED))
                .oneAs(Reservation.class);

        if (reservation == null) {
            throw new BusinessException("您还未有确认的座位，无法离开座位");
        }

        Seat seat = seatMapper.lockSeat(reservation.getSeatId());
        reservation.setStatus(SeatConstant.RESERVATION_STATUS_COMPLETED);
        reservation.setUpdateTime(LocalDateTime.now());
        reservationMapper.update(reservation, true);
        log.info("释放座位，释放信息：{}", reservation);

        seat.setSeatStatus(SeatConstant.SEAT_STATUS_AVAILABLE);
        seat.setUpdateTime(LocalDateTime.now());
        seatMapper.update(seat, true);
        log.info("释放座位，座位信息：{}", seat);
    }

}

