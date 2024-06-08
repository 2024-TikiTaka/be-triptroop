package com.tikitaka.triptroop.common.exception;

import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class AuthException extends BaseException {

    private static final int DEFAULT_EXCEPTION_CODE = 401;

    public AuthException(String message) {
        super(DEFAULT_EXCEPTION_CODE, message);
    }

    public AuthException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
