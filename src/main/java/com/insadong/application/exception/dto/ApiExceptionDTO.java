package com.insadong.application.exception.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

public class ApiExceptionDTO {

	@Data
	public class ApiExceptionDto {

		private int state;
		private String message;

		public ApiExceptionDto(HttpStatus status, String message) {
			this.state = status.value();
			this.message = message;
		}

	}

}