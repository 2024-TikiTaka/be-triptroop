package com.tikitaka.triptroop.common.dto.response;

import lombok.Getter;

@Getter
public class CommonResponse<T> {

    private boolean success;

    private String message;

    private T data;

    public CommonResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(true, null, data);
    }

    public static <T> CommonResponse<T> fail(String message) {
        return new CommonResponse<>(false, message, null);
    }
}
