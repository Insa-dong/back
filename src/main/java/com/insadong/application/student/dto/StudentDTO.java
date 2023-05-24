package com.insadong.application.student.dto;

import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class StudentDTO {

	private Long stuCode;
	private String stuName;
	private String stuEngName;
	private String stuPhone;
	private String stuEmail;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date stuBirth;
	private String stuEndSchool;


}
