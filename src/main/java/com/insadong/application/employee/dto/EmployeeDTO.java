package com.insadong.application.employee.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class EmployeeDTO {

	private Long empCode;
	private String deptCode;
	private String jobCode;
	private String empName;
	private String empGender;
	private String email;
	private String empId;
	private String empPwd;
	private String empPhone;
	private Date hireDate;
	private Long offCount;
	private String empState;
	private Date endDate;
	
}
