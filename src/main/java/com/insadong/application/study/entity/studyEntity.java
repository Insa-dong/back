package com.insadong.application.study.entity;

import com.insadong.application.common.entity.StudyTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_STUDY")
public class studyEntity {

	@Id
	@Column(name = "STUDY_CODE")
	private Long studyCode;

	@OneToMany
	@JoinColumn(name = "STUDY_CODE")
	private List<StudyTime> studyTimes;

	@ManyToOne
	@JoinColumn(name = "TRAINING_CODE")
	private trainingEntity training;

	@Column(name = "STUDY_START_DATE")
	private String studyStartDate;

	@Column(name = "STUDY_END_DATE")
	private String studyEndDate;

	@Column(name = "STUDY_MAX_PEOPLE")
	private Long studyMaxPeople;

	@Column(name = "STUDY_DELETE_YN")
	private String studyDeleteYn;

}
