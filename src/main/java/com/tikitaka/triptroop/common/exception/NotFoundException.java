package com.tikitaka.triptroop.common.exception;

import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class NotFoundException extends BaseException {

    public NotFoundException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
