package com.tikitaka.triptroop.common.exception;

import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class ConflictException extends BaseException {

    private static final int DEFAULT_EXCEPTION_CODE = 409;

    public ConflictException(String message) {
        super(DEFAULT_EXCEPTION_CODE, message);
    }

    public ConflictException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
