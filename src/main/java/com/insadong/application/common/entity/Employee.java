package com.insadong.application.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_EMP")
public class Employee {
	@Id
	@Column(name = "EMP_CODE")
	public Long empCode;
}
