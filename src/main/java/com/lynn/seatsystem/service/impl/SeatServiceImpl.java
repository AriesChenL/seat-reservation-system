package com.lynn.seatsystem.service.impl;

import com.lynn.seatsystem.constant.SeatConstant;
import com.lynn.seatsystem.domain.entity.Floor;
import com.lynn.seatsystem.domain.vo.SeatVO;
import com.lynn.seatsystem.exception.BusinessException;
import com.lynn.seatsystem.mapper.FloorMapper;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lynn.seatsystem.domain.entity.Seat;
import com.lynn.seatsystem.mapper.SeatMapper;
import com.lynn.seatsystem.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lynn.seatsystem.domain.entity.table.FloorTableDef.FLOOR;
import static com.lynn.seatsystem.domain.entity.table.SeatTableDef.SEAT;

/**
 * 座位表 服务层实现。
 *
 * @author root
 * @since 2024-10-06
 */
@Service
@RequiredArgsConstructor
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements SeatService {
    private final FloorMapper floorMapper;
    private final SeatMapper seatMapper;

    @Override
    public void createSeat(Seat seat) {
        Floor floor = floorMapper.selectOneById(seat.getFloorId());
        if (floor == null) {
            throw new BusinessException("楼层不存在");
        }

        Seat one = this.queryChain()
                .select()
                .where(SEAT.FLOOR_ID.eq(seat.getFloorId()))
                .and(SEAT.SEAT_NUMBER.eq(seat.getSeatNumber()))
                .one();
        if (one != null) {
            throw new BusinessException("该楼层已存在该座位");
        }

        this.checkSeatType(seat.getSeatType());
        this.save(seat);
    }

    @Override
    public SeatVO getSeat(Long id) {
        List<SeatVO> seatList = QueryChain.of(seatMapper)
                .select(
                        SEAT.SEAT_ID,
                        FLOOR.LIBRARY_ID,
                        FLOOR.FLOOR_NUMBER,
                        SEAT.SEAT_NUMBER,
                        SEAT.SEAT_TYPE,
                        SEAT.AVAILABLE)
                .from(SEAT)
                .leftJoin(FLOOR).on(SEAT.FLOOR_ID.eq(FLOOR.FLOOR_ID))
                .where(SEAT.SEAT_ID.eq(id))
                .listAs(SeatVO.class);
        if (seatList.isEmpty()) {
            throw new BusinessException("座位不存在");
        }
        return seatList.getFirst();
    }

    @Override
    public List<SeatVO> getAvailableSeat(Long floorId) {
        return QueryChain.of(seatMapper)
                .select()
                .from(SEAT)
                .where(SEAT.FLOOR_ID.eq(floorId))
                .and(SEAT.AVAILABLE.eq(true))
                .listAs(SeatVO.class);
    }

    private void checkSeatType(String seatType) {
        if (!SeatConstant.SEAT_TYPE_LIST.contains(seatType)) {
            throw new BusinessException("座位类型不合法");
        }
    }
}
