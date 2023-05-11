package com.insadong.application.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "TB_DEPT")
public class Dept {

	@Id
	@Column(name = "DEPT_CODE")
	private String deptCode;

	@Column(name = "DEPT_NAME")
	private String deptName;
}
