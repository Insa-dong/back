package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_STUDY_INFO")
@SequenceGenerator(name = "STUDY_INFO_SEQ_GEN", sequenceName = "SEQ_STUDY_INFO_CODE", allocationSize = 1, initialValue = 1)
public class StudyInfo {

	@Id
	@Column(name = "STUDY_INFO_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDY_INFO_CODE")
	private Long studyInfoCode;

	@ManyToOne
	@JoinColumn(name = "STUDY")
	private Study study;

	@ManyToOne
	@JoinColumn(name = "TEACHER")
	private Employee teacher;

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
