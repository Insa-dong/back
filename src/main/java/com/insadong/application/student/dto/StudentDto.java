package com.insadong.application.student.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StudentDto {

	private Long stuCode;
	private String stuName;
	private String stuEngName;
	private String stuPhone;
	private String stuEmail;
	private Date stuBirth;
	private String stuEndSchool;
	
	
}
