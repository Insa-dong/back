package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_STUDY_STU")
@SequenceGenerator(name = "STUDY_STU_SEQ_GEN", sequenceName = "SEQ_STUDY_STU_CODE", allocationSize = 1)
public class StudyStu {

	@Id
	@Column(name = "STUDY_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDY_STU_CODE")
	private Long studyCode;

	@ManyToOne
	@JoinColumn(name = "STU_CODE")
	private Student student;

	@Column(name = "STUDY_ENROLL_DATE")
	private String studyEnrollDate;

	@Column(name = "STUDY_STATE")
	private String studyState;
}
