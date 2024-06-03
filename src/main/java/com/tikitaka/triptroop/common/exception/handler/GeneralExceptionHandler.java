package com.tikitaka.triptroop.common.exception.handler;


import com.tikitaka.triptroop.common.dto.response.ApiResponse;
import com.tikitaka.triptroop.common.exception.BadRequestException;
import com.tikitaka.triptroop.common.exception.ConflictException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.ServerInternalException;
import com.tikitaka.triptroop.common.exception.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {

    /* 400, BadRequestException */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> badRequestException(BadRequestException e) {

        log.info("BadRequestException : {}", e.getMessage());
        final ErrorResponse errorResponse = ErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail(errorResponse));
    }

    /* 404, NotFoundException */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> notFoundException(NotFoundException e) {

        log.info("NotFoundException : {}", e.getMessage());
        final ErrorResponse errorResponse = ErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail(errorResponse));
    }

    /* 409, ConflictException: 논리적으로 수행할 수 없을 경우 처리  */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse> conflictException(ConflictException e) {

        log.info("ConflictException : {}", e.getMessage());
        final ErrorResponse errorResponse = ErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.fail(errorResponse));
    }

    /* @Valid 에 대한 Exception */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        log.info("MethodArgumentNotValidException : {}", e.getMessage());
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        final ErrorResponse errorResponse = ErrorResponse.of(400, defaultMessage);
        return ResponseEntity.badRequest().body(ApiResponse.fail(errorResponse));
    }

    /* 500, ServerError */
    @ExceptionHandler(ServerInternalException.class)
    public ResponseEntity<ApiResponse> serverInternalException(ServerInternalException e) {

        log.info("ServerInternalException : {}", e.getMessage());
        final ErrorResponse errorResponse = ErrorResponse.of(e.getCode(), e.getMessage());
        return ResponseEntity.internalServerError().body(ApiResponse.fail(errorResponse));
    }

    /* Global Exception */
    @ExceptionHandler({ Exception.class, RuntimeException.class })
    public ResponseEntity<ApiResponse> exception(Exception e) {

        log.info("Exception : {}", e.getMessage());
        final ErrorResponse errorResponse = ErrorResponse.of(500, e.getMessage());
        return ResponseEntity.internalServerError().body(ApiResponse.fail(errorResponse));
    }
}
