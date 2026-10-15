package com.ecomm.product.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecomm.product.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(
            AppException exception) {

        ApiResponse<Object> response = new ApiResponse<>(exception.getMessage(),null, exception.getHttpStatus() );

        return new ResponseEntity<>( response, exception.getHttpStatus()  );
    }
}