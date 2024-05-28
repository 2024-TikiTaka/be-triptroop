package com.tikitaka.triptroop.common.exception;

import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import lombok.Getter;

@Getter
public class ServerInternalException extends BaseException {

    public ServerInternalException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
