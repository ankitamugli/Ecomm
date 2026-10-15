package com.dcl.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dcl.user.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleException(AppException exception) {

        return new ResponseEntity<>(
                new ApiResponse<>(
                        exception.getMessage(),
                        null,
                        exception.getHttpStatus()
                ),
                exception.getHttpStatus()
        );
    }
}