package com.insadong.application.common.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_EMP")
@SequenceGenerator(name="EMP_SEQ_GENERATOR",
		sequenceName="SEQ_EMP_CODE",
		initialValue=1, allocationSize=1)
public class Employee {
	@Id
	@Column(name = "EMP_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMP_SEQ_GENERATOR")
	private Long empCode;

	@ManyToOne
	@JoinColumn(name = "DEPT_CODE")
	private Dept dept;

	@ManyToOne
	@JoinColumn(name = "JOB_CODE")
	private Job job;

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
	
	@OneToMany
	@JoinColumn(name="EMP_CODE")
	private List<EmpAuth> empAuthList;
	


}



