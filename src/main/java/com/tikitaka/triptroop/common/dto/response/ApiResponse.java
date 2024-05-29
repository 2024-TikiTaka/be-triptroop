package com.tikitaka.triptroop.common.dto.response;

import com.tikitaka.triptroop.common.exception.dto.response.ErrorResponse;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private T result;

    public ApiResponse(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null);
    }

    public static <T> ApiResponse<T> success(T data) { return new ApiResponse<>(true, null, data); }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> fail(String message) { return new ApiResponse<>(false, message, null); }

    public static ApiResponse<ErrorResponse> fail(ErrorResponse errorResponse) { return new ApiResponse<>(false, null, errorResponse); }
}
