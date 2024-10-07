package com.lynn.seatsystem.exception;

import com.lynn.seatsystem.domain.vo.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午7:48
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<String> handleCustomException(BusinessException ex, WebRequest request) {
        return ApiResponse.error(ex.getErrorMessage());
    }
}