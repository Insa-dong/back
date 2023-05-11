package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_CLASS")
@SequenceGenerator(name = "CLASS_SEQ_GEN", sequenceName = "SEQ_CLASS_CODE", allocationSize = 1)
public class Class {

	@Id
	@Column(name = "CLASS_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASS_CODE")
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
