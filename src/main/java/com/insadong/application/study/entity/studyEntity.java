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

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "STUDY_CODE")
	private List<StudyTime> studyTimes;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
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

	@ManyToOne
	@JoinColumn(name = "STUDY_MODIFIER")
	private empEntity studyModifier;

	@Column(name = "STUDY_MODIFY_DATE")
	private String studyModifyDate;
}
