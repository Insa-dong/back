package com.insadong.application.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EmployeeDTO {

	private Long empCode;
	private DeptDTO dept;
	private JobDTO job;
	private String empName;
	private String empGender;
	private String empEmail;
	private String empId;
	private String empPwd;
	private String empPhone;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	private Long offCount;
	private String empState;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
}
