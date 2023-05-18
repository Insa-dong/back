package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TB_STUDY_TIME")
@SequenceGenerator(name = "STUDY_TIME_SEQ_GEN", sequenceName = "SEQ_STUDY_TIME_CODE", allocationSize = 1)
public class StudyTime {

	@Id
	@Column(name = "STUDY_TIME_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDY_TIME_SEQ_GEN")
	private Long studyTimeCode;

	@Column(name = "STUDY_CODE")
	private Long studyCode;

	@Column(name = "STUDY_DATE")
	private String studyDate;

	@Column(name = "STUDY_START_TIME")
	private Date studyStartTime;

	@Column(name = "STUDY_END_TIME")
	private Date studyEndTime;

}