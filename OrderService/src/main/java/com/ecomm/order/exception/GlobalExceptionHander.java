package com.ecomm.order.exception;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

public class GlobalExceptionHander extends RuntimeException{
	
	public ResponseEntity<?> handleException(AppException exception){
		return new ReponseEntity<>(new ApiResponse<>(exception.getMessage(),null,exception.gethttpStatus()),exception.gethttpStatus());
		
	}

}
