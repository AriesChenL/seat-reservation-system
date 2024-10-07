package com.lynn.seatsystem.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午6:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 失败状态码
     */
    public static final int ERROR_CODE = 500;

    /**
     * 成功返回结果
     *
     * @param data 返回数据
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_CODE, "操作成功", data);
    }

    /**
     * 成功返回结果
     *
     * @param message 返回信息
     * @param data    返回数据
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(SUCCESS_CODE, message, data);
    }

    /**
     * 失败返回结果
     *
     * @param message 错误信息
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(ERROR_CODE, message, null);
    }

    /**
     * 失败返回结果
     *
     * @param code    错误码
     * @param message 错误信息
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
