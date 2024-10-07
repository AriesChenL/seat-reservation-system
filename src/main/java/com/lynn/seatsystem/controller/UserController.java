package com.lynn.seatsystem.controller;

import com.lynn.seatsystem.domain.dto.UserDTO;
import com.lynn.seatsystem.domain.entity.User;
import com.lynn.seatsystem.domain.vo.ApiResponse;
import com.lynn.seatsystem.domain.vo.UserVO;
import com.lynn.seatsystem.service.UserService;
import com.lynn.seatsystem.util.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午6:10
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    /**
     * 创建新用户
     *
     * @param user 要创建的用户信息
     * @return 包含创建的用户信息的 ApiResponse
     */
    @PostMapping("/create")
    public ApiResponse<UserVO> createUser(@RequestBody UserDTO user) {
        User target = new User();
        BeanUtils.copyProperties(user, target);
        UserVO createdUser = userService.createUser(target);
        return ApiResponse.success("用户创建成功", createdUser);
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 包含登录令牌的 ApiResponse，如果登录失败则返回错误信息
     */
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestParam String username, @RequestParam String password) {
        if (username == null || password == null) {
            return ApiResponse.error("用户名或密码不能为空");
        }

        String token = userService.login(username, password);
        if (token != null) {
            return ApiResponse.success("登录成功", token);
        } else {
            return ApiResponse.error("用户名或密码错误");
        }
    }
}
