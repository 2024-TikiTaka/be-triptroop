package com.tikitaka.triptroop.common.exception.handler;

import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.ServerInternalException;
import com.tikitaka.triptroop.common.exception.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    /* 404, NotFoundException */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> notFoundException(NotFoundException e) {

        final ApiErrorResponse errorResponse = ApiErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /* 409, ConflictException: 논리적으로 수행할 수 없을 경우 처리  */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorResponse> conflictException(ConflictException e) {

        final ApiErrorResponse errorResponse = ApiErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /* Valid Exception */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        final ApiErrorResponse errorResponse = ApiErrorResponse.of(9000, defaultMessage);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    /* 500, ServerError */
    @ExceptionHandler(ServerInternalException.class)
    public ResponseEntity<ApiErrorResponse> serverInternalException(ServerInternalException e) {

        final ApiErrorResponse errorResponse = ApiErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    /* Global Exception */
    @ExceptionHandler({ Exception.class, RuntimeException.class })
    public ResponseEntity<ApiErrorResponse> exception(Exception e) {

        final ApiErrorResponse errorResponse = ApiErrorResponse.of(9999, e.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
