package com.insadong.application.employee.dto;

import lombok.Data;

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
	private Date hireDate;
	private Long offCount;
	private String empState;
	private Date endDate;

}
