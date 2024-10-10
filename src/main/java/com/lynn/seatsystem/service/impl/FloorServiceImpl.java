package com.lynn.seatsystem.service.impl;

import com.lynn.seatsystem.domain.vo.FloorVO;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lynn.seatsystem.domain.entity.Floor;
import com.lynn.seatsystem.mapper.FloorMapper;
import com.lynn.seatsystem.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lynn.seatsystem.domain.entity.table.FloorTableDef.FLOOR;

/**
 * 楼层表 服务层实现。
 *
 * @author root
 * @since 2024-10-06
 */
@Service
@RequiredArgsConstructor
public class FloorServiceImpl extends ServiceImpl<FloorMapper, Floor> implements FloorService {
    private final FloorMapper floorMapper;

    @Override
    public List<FloorVO> getAvailableFloor(Long libraryId) {
        return QueryChain.of(floorMapper)
                .select()
                .from(FLOOR)
                .where(FLOOR.LIBRARY_ID.eq(libraryId))
                .listAs(FloorVO.class);
    }
}

