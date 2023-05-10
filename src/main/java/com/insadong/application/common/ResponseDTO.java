package com.insadong.application.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDTO {

	private int status;
	private String message;
	private Object data;

	public ResponseDTO(HttpStatus status, String message, Object data) {
		this.status = status.value();
		this.message = message;
		this.data = data;
	}

	public ResponseDTO(HttpStatus httpStatus, String message) {
		this.status = httpStatus.value();
		this.message = message;
	}
}
