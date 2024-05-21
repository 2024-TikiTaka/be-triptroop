package com.tikitaka.triptroop.common.exception;

import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class ConflictException extends CustomException {
    public ConflictException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
