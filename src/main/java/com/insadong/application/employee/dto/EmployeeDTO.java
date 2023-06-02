package com.insadong.application.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.emporg.dto.EmpAuthDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	private String newPwd;
	private String checkPwd;

	private List<EmpAuthDTO> empAuthList;
}
