package com.tikitaka.triptroop.common.exception;

import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class ForbiddenException extends BaseException {

    private static final int DEFAULT_EXCEPTION_CODE = 403;

    public ForbiddenException(String message) {
        super(DEFAULT_EXCEPTION_CODE, message);
    }

    public ForbiddenException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
