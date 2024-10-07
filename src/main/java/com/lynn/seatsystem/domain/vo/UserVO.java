package com.lynn.seatsystem.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午6:53
 */
@Data
public class UserVO {
    private Long userId;
    private String username;
    private String email;
    private String phone;
    private Boolean gender;
    private Boolean status;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
