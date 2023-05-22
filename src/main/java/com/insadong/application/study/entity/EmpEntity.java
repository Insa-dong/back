package com.insadong.application.study.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "TB_EMP")
public class EmpEntity {

	@Id
	@Column(name = "EMP_CODE")
	private Long empCode;

	@Column(name = "EMP_NAME")
	private String empName;

}
