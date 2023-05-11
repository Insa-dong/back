package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TB_EMP")
public class Employee {
	@Id
	@Column(name = "EMP_CODE")
	private Long empCode;

	@ManyToOne
	@JoinColumn(name = "DEPT_CODE")
	private Dept deptCode;

	@ManyToOne
	@JoinColumn(name = "JOB_CODE")
	private Job jobCode;

	@Column(name = "EMP_NAME")
	private String empName;

	@Column(name = "EMP_GENDER")
	private String empGender;

	@Column(name = "EMP_EMAIL")
	private String empEmail;

	@Column(name = "EMP_ID")
	private String empId;

	@Column(name = "EMP_PWD")
	private String empPwd;

	@Column(name = "EMP_PHONE")
	private String empPhone;

	@Column(name = "HIRE_DATE")
	private Date hireDate;

	@Column(name = "OFF_COUNT")
	private Long offCount;

	@Column(name = "EMP_STATE")
	private String empState;

	@Column(name = "END_DATE")
	private Date endDate;


}



