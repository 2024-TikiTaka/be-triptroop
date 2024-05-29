package com.tikitaka.triptroop.common.exception.handler;

import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.ServerInternalException;
import com.tikitaka.triptroop.common.exception.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    /* 404, NotFoundException */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> notFoundException(NotFoundException e) {

        final ErrorResponse errorResponse = ErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ApiResponse.fail(errorResponse));
    }

    /* 409, ConflictException: 논리적으로 수행할 수 없을 경우 처리  */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse> conflictException(ConflictException e) {

        final ErrorResponse errorResponse = ErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(ApiResponse.fail(errorResponse));
    }

    /* @Valid 에 대한 Exception */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        final ErrorResponse errorResponse = ErrorResponse.of(9000, defaultMessage);
        return ResponseEntity.badRequest()
                             .body(ApiResponse.fail(errorResponse));
    }

    /* 500, ServerError */
    @ExceptionHandler(ServerInternalException.class)
    public ResponseEntity<ApiResponse> serverInternalException(ServerInternalException e) {

        final ErrorResponse errorResponse = ErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.internalServerError()
                             .body(ApiResponse.fail(errorResponse));
    }

    /* Global Exception */
    @ExceptionHandler({ Exception.class, RuntimeException.class })
    public ResponseEntity<ApiResponse> exception(Exception e) {

        final ErrorResponse errorResponse = ErrorResponse.of(9999, e.getMessage());
        return ResponseEntity.internalServerError()
                             .body(ApiResponse.fail(errorResponse));
    }
}
