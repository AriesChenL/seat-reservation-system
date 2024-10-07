package com.lynn.seatsystem.domain.dto;

import lombok.Data;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午6:52
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
    private Boolean gender;
}
