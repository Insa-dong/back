package com.insadong.application.common.entity;

import com.insadong.application.study.entity.studyEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_STUDY_TIME")
@SequenceGenerator(name = "STUDY_TIME_SEQ_GEN", sequenceName = "SEQ_STUDY_TIME_CODE", allocationSize = 1, initialValue=1)
public class StudyTime {

	@Id
	@Column(name = "STUDY_TIME_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDY_TIME_SEQ_GEN")
	private Long studyTimeCode;

	@ManyToOne
	@JoinColumn(name = "STUDY_CODE")
	private studyEntity study;

	@Column(name = "STUDY_DATE")
	private String studyDate;

	@Column(name = "STUDY_START_TIME")
	private String studyStartTime;

	@Column(name = "STUDY_END_TIME")
	private String studyEndTime;

}