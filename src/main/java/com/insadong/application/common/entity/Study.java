package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_STUDY")
@SequenceGenerator(name = "STUDY_SEQ_GEN", sequenceName = "SEQ_STUDY_CODE", allocationSize = 1)
public class Study {

	@Id
	@Column(name = "STUDY_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDY_CODE")
	private Long studyCode;

	@ManyToOne
	@JoinColumn(name = "TRAINING_CODE")
	private Training training;

	@Column(name = "STUDY_START_DATE")
	private String studyStartDate;

	@Column(name = "STUDY_END_DATE")
	private String studyEndDate;

	@Column(name = "STUDY_MAX_PEOPLE")
	private Long studyMaxPeople;

	@Column(name = "STUDY_MODIFIER")
	private String studyModifier;
}
