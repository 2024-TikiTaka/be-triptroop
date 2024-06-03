package com.tikitaka.triptroop.common.exception;

import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {

    private static final int DEFAULT_EXCEPTION_CODE = 400;

    public BadRequestException(String message) {
        super(DEFAULT_EXCEPTION_CODE, message);
    }

    public BadRequestException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
