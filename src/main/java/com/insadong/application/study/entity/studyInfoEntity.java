package com.insadong.application.study.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "TB_STUDY_INFO")
public class studyInfoEntity {

	@Id
	@Column(name = "STUDY_INFO_CODE")
	private Long studyInfoCode;

	@ManyToOne
	@JoinColumn(name = "STUDY")
	private studyEntity study;

	@ManyToOne
	@JoinColumn(name = "TEACHER")
	private empEntity teacher;

	@Column(name = "STUDY_INFO_START_DATE")
	private String studyInfoStartDate;

	@Column(name = "STUDY_INFO_END_DATE")
	private String studyInfoEndDate;

	@Column(name = "STUDY_ROOM")
	private String studyRoom;

	@Column(name = "STUDY_TITLE")
	private String studyTitle;

	@Column(name = "STUDY_CONTENT")
	private String studyContent;

}
