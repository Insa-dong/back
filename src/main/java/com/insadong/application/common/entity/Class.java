package com.insadong.application.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "TB_CLASS")
public class Class {

	@Id
	@Column(name = "CLASS_CODE")
	private Long classCode;

	@ManyToOne
	@JoinColumn(name = "TRAINING_CODE")
	private Training trainingCode;

	@Column(name = "CLASS_START_DATE")
	private String classStartDate;

	@Column(name = "CLASS_END_DATE")
	private String classEndDate;

	@Column(name = "CLASS_MAX_PEOPLE")
	private Long classMaxPeople;

	@Column(name = "CLASS_MODIFIER")
	private String classModifier;
}
