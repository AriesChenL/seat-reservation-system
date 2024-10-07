package com.lynn.seatsystem.exception;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午7:49
 */
public class BusinessException extends RuntimeException {

    private final String errorMessage;

    public BusinessException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }
}
