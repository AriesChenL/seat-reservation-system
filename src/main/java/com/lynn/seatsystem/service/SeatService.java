package com.lynn.seatsystem.service;

import com.lynn.seatsystem.domain.vo.SeatVO;
import com.mybatisflex.core.service.IService;
import com.lynn.seatsystem.domain.entity.Seat;

/**
 * 座位表 服务层。
 *
 * @author root
 * @since 2024-10-06
 */
public interface SeatService extends IService<Seat> {

    /**
     * 新增座位
     * @param seat 座位实体
     */
    void createSeat(Seat seat);

    /**
     * 获取座位信息
     * @param id 座位id
     * @return SeatVO
     */
    SeatVO getSeat(Long id);
}
