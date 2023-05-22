package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "TB_EMP")
@SequenceGenerator(name = "EMP_SEQ_GENERATOR",
		sequenceName = "SEQ_EMP_CODE",
		initialValue = 1, allocationSize = 1)
public class Employee {
	@Id
	@Column(name = "EMP_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ_GENERATOR")
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
	private Double offCount;

	@Column(name = "EMP_STATE")
	private String empState;

	@Column(name = "END_DATE")
	private Date endDate;

	@OneToMany
	@JoinColumn(name = "EMP_CODE")
	private List<EmpAuth> empAuthList;

	@OneToMany(mappedBy = "signRequester")
    private List<Off> offs;

}



