package com.lynn.seatsystem.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lynn.seatsystem.domain.entity.Feedback;
import com.lynn.seatsystem.mapper.FeedbackMapper;
import com.lynn.seatsystem.service.FeedbackService;
import org.springframework.stereotype.Service;

/**
 * 反馈表 服务层实现。
 *
 * @author root
 * @since 2024-10-06
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

}
