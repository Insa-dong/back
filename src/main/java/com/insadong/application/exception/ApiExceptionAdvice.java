package com.insadong.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.insadong.application.exception.dto.ApiExceptionDTO;

@RestControllerAdvice
public class ApiExceptionAdvice {

	@ExceptionHandler({ UserNotFoundException.class, LoginFailedException.class, IllegalArgumentException.class })
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(Exception e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));

	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(RuntimeException e) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));

	}

}
