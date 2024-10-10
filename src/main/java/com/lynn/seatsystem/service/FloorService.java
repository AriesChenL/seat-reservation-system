package com.lynn.seatsystem.service;

import com.lynn.seatsystem.domain.vo.FloorVO;
import com.mybatisflex.core.service.IService;
import com.lynn.seatsystem.domain.entity.Floor;

import java.util.List;

/**
 * 楼层表 服务层。
 *
 * @author root
 * @since 2024-10-06
 */
public interface FloorService extends IService<Floor> {

    /**
     * 获取可用楼层
     * @param libraryId 图书馆ID
     * @return 可用楼层列表
     */
    List<FloorVO> getAvailableFloor(Long libraryId);
}
