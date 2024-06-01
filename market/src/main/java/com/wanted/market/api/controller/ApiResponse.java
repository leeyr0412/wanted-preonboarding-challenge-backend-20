package com.wanted.market.api.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private final boolean result;
    private final int code;
    private final HttpStatus status;
    private final String message;
    private final T data;

    private ApiResponse(boolean result, HttpStatus status, String message, T data) {
        this.result = result;
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(HttpStatus status, String message) {
        return new ApiResponse<>(false, status, message, null);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, HttpStatus.OK, "Success", data);
    }
}
