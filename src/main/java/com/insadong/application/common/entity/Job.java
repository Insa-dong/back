package com.insadong.application.common.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "TB_JOB")
public class Job {

	@Id
	@Column(name = "JOB_CODE")
	private String jobCode;

	@Column(name = "JOB_NAME")
	private String jobName;
}
