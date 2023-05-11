package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_CLASS_TIME")
@SequenceGenerator(name = "CLASS_TIME_SEQ_GEN", sequenceName = "SEQ_CLASS_TIME_CODE", allocationSize = 1)
public class ClassTime {

	@Id
	@Column(name = "CLASS_TIME_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASS_TIME_CODE")
	private Long classTimeCode;

	@ManyToOne
	@JoinColumn(name = "CLASS_CODE")
	private Class classCode;

	@Column(name = "CLASS_DATE")
	private String classDate;

	@Column(name = "CLASS_START_TIME")
	private String classStartTime;

	@Column(name = "CLASS_END_TIME")
	private String classEndTime;

}
