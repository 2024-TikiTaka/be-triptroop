package com.tikitaka.triptroop.common.exception;

import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class NotFoundException extends BaseException {

    private static final int DEFAULT_EXCEPTION_CODE = 404;

    public NotFoundException(String message) {
        super(DEFAULT_EXCEPTION_CODE, message);
    }

    public NotFoundException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
