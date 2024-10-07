package com.lynn.seatsystem.service;

import com.lynn.seatsystem.domain.vo.UserVO;
import com.mybatisflex.core.service.IService;
import com.lynn.seatsystem.domain.entity.User;

/**
 * 用户表 服务层。
 *
 * @author root
 * @since 2024-10-06
 */
public interface UserService extends IService<User> {

    /**
     * 新增用户
     * @param target 目标对象
     * @return userVO
     */
    UserVO createUser(User target);

    /**
     * 用户登录
     * @param username username
     * @param password password
     * @return token
     */
    String login(String username, String password);
}
