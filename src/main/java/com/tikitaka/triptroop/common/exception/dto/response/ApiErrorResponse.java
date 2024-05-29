package com.tikitaka.triptroop.common.exception.dto.response;

import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiErrorResponse {

    private final int code;

    private final String message;

    public ApiErrorResponse(ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public static ApiErrorResponse of(int code, String message) {
        return new ApiErrorResponse(code, message);
    }
}
