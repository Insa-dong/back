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
@Table(name = "TB_JOB")
public class Job {

	@Id
	@Column(name = "JOB_CODE")
	private String jobCode;

	@Column(name = "JOB_NAME")
	private String jobName;
}
