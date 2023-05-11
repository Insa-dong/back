package com.insadong.application.employee.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "TB_EMP")
public class Employee {
	@Id
	@Column(name = "EMP_CODE")
	private Long empCode;
	
	@Column(name="DEPT_CODE")
	private String deptCode;
	
	@Column(name="JOB_CODE")
	private String jobCode;
	
	@Column(name="EMP_NAME")
	private String empName;
	
	@Column(name="EMP_GENDER")
	private String empGender;
	
	@Column(name="EMP_EMAIL")
	private String email;
	
	@Column(name="EMP_ID")
	private String empId;
	
	@Column(name="EMP_PWD")
	private String empPwd;
	
	@Column(name="EMP_PHONE")
	private String empPhone;
	
	@Column(name="HIRE_DATE")
	private Date hireDate;
	
	@Column(name="OFF_COUNT")
	private Long offCount;
	
	@Column(name="EMP_STATE")
	private String empState;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	
}
