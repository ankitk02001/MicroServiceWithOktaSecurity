package com.ankit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ankit.exception.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFounException.class)
	public ResponseEntity<ApiResponse> handleResourseNotFoundException(ResourceNotFounException ex){
		String message = ex.getMessage();
		
		//Setting value in object using builder() method
		ApiResponse response = ApiResponse.builder().message(message)
				.sucess(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
