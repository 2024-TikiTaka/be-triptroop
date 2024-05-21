package com.tikitaka.triptroop.common.exception.handler;

import com.tikitaka.triptroop.common.exception.ServerInternalException;
import com.tikitaka.triptroop.common.exception.dto.response.ErrorResponse;
import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {

    /* Not Found Exception */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException e) {

        final ErrorResponse exceptionResponse = ErrorResponse.of(e.getCode(), e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    /* Server Error */
    @ExceptionHandler(ServerInternalException.class)
    public ResponseEntity<ErrorResponse> serverInternalException(ServerInternalException e) {

        final ErrorResponse exceptionResponse = ErrorResponse.of(e.getCode(), e.getMessage());

        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    /* Conflict : 충돌 (논리적으로 수행할 수 없을 경우 처리) 409 */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> conflictException(ConflictException e) {

        final ErrorResponse exceptionResponse = ErrorResponse.of(e.getCode(), e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    /* Valid Exception */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();

        final ErrorResponse exceptionResponse = ErrorResponse.of(9000, defaultMessage);

        return ResponseEntity.badRequest().body(exceptionResponse);
    }








}
