package com.lynn.seatsystem.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lynn.seatsystem.domain.entity.Floor;
import com.lynn.seatsystem.mapper.FloorMapper;
import com.lynn.seatsystem.service.FloorService;
import org.springframework.stereotype.Service;

/**
 * 楼层表 服务层实现。
 *
 * @author root
 * @since 2024-10-06
 */
@Service
public class FloorServiceImpl extends ServiceImpl<FloorMapper, Floor> implements FloorService {

}
