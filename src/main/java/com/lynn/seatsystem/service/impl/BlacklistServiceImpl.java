package com.lynn.seatsystem.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lynn.seatsystem.domain.entity.Blacklist;
import com.lynn.seatsystem.mapper.BlacklistMapper;
import com.lynn.seatsystem.service.BlacklistService;
import org.springframework.stereotype.Service;

/**
 * 黑名单表 服务层实现。
 *
 * @author root
 * @since 2024-10-06
 */
@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements BlacklistService {

}
