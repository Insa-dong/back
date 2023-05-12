package com.insadong.application.student.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudentDTO {

	private Long stuCode;
	private String stuName;
	private String stuEngName;
	private String stuPhone;
	private String stuEmail;
	private Date stuBirth;
	private String stuEndSchool;


}
