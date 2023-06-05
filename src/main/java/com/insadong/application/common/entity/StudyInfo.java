package com.insadong.application.common.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_STUDY_INFO")
@SequenceGenerator(name = "STUDY_INFO_SEQ_GEN", sequenceName = "SEQ_STUDY_INFO_CODE", allocationSize = 1, initialValue = 1)
public class StudyInfo {

	@Id
	@Column(name = "STUDY_INFO_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDY_INFO_SEQ_GEN")
	private Long studyInfoCode;

	@JsonIgnore
	@OneToMany(mappedBy = "StudyInfo", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Eva> eva;

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
