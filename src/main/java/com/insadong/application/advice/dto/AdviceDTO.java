package com.insadong.application.advice.dto;

import java.util.Date;

import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.student.dto.StudentDTO;

import lombok.Data;

@Data
public class AdviceDTO {
	
	
	private Long adviceLogCode;
	private StudentDTO student;
	private EmployeeDTO Writer;
	private String adviceLogContent;
	private Date adviceLogDate;
	private String adviceLogDelete;

}
