package com.insadong.application.advice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.common.entity.Employee;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.student.dto.StudentDTO;

import lombok.Data;

@Data
public class AdviceDTO {
	
	
	private Long adviceLogCode;
	private StudentDTO student;
	private Employee writer;
	private String adviceLogContent;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date adviceLogDate;
	private String adviceLogUpdate;
	private String adviceLogDelete;
	
	private Long stuCode;
	private Long empCode;

}
