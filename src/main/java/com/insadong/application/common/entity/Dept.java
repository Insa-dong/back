package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "TB_DEPT")
public class Dept {

	@Id
	@Column(name = "DEPT_CODE")
	private String deptCode;

	@Column(name = "DEPT_NAME")
	private String deptName;
}
