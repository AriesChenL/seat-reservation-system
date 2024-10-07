package com.lynn.seatsystem.service;

import com.mybatisflex.core.service.IService;
import com.lynn.seatsystem.domain.entity.Reservation;

/**
 * 预约表 服务层。
 *
 * @author root
 * @since 2024-10-06
 */
public interface ReservationService extends IService<Reservation> {

    /**
     * 创建预约
     * @param seatId 座位id
     * @param userId 用户id
     */
    void createReservation(long seatId, long userId);

    /**
     * 取消预约
     * @param reservationId 预约id
     * @param userId 用户id
     */
    void cancelReservation(long reservationId, long userId);

    /**
     * 确认预约
     * @param reservationId 预约id
     * @param userId 用户id
     */
    void confirmReservation(long reservationId, long userId);

    /**
     * 离开座位
     * @param reservationId 预约id
     * @param userId 用户id
     */
    void leaveSeat(long reservationId, long userId);
}
