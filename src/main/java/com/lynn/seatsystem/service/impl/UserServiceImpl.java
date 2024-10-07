package com.lynn.seatsystem.service.impl;

import com.lynn.seatsystem.domain.vo.UserVO;
import com.lynn.seatsystem.exception.BusinessException;
import com.lynn.seatsystem.util.JwtUtils;
import com.lynn.seatsystem.util.PasswordUtils;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lynn.seatsystem.domain.entity.User;
import com.lynn.seatsystem.mapper.UserMapper;
import com.lynn.seatsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.lynn.seatsystem.domain.entity.table.UserTableDef.USER;

/**
 * 用户表 服务层实现。
 *
 * @author root
 * @since 2024-10-06
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    @Override
    public UserVO createUser(User target) {
        if (getUserByUsername(target.getUsername()) != null) {
            throw new BusinessException("用户已存在");
        }

        target.setPassword(PasswordUtils.hashPassword(target.getPassword()));
        this.save(target);

        User savedUser = this.getUserByUsername(target.getUsername());
        return convertToUserVO(savedUser);
    }

    @Override
    public String login(String username, String password) {
        User user = this.getUserByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        if (!PasswordUtils.verifyPassword(password, user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        return JwtUtils.generateToken(user.getUserId());
    }

    private User getUserByUsername(String username) {
        return QueryChain.of(userMapper)
                .select()
                .from(USER)
                .where(USER.USERNAME.eq(username))
                .limit(1)
                .one();
    }

    private UserVO convertToUserVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
