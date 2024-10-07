package com.lynn.seatsystem.mapper;

import com.mybatisflex.core.BaseMapper;
import com.lynn.seatsystem.domain.entity.Seat;
import org.apache.ibatis.annotations.Param;

/**
 * 座位表 映射层。
 *
 * @author root
 * @since 2024-10-06
 */
public interface SeatMapper extends BaseMapper<Seat> {

    /**
     * 锁定座位
     * @param seatId 座位id
     * @return seat
     */
    Seat lockSeat(@Param("seatId") long seatId);
}
