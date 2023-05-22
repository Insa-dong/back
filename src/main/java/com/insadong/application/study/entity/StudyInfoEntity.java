package com.insadong.application.study.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_STUDY_INFO")
public class StudyInfoEntity {

	@Id
	@Column(name = "STUDY_INFO_CODE")
	private Long studyInfoCode;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "STUDY")
	private StudyEntity study;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "TEACHER")
	private EmpEntity teacher;

	@Column(name = "STUDY_INFO_START_DATE")
	private Date studyInfoStartDate;

	@Column(name = "STUDY_INFO_END_DATE")
	private Date studyInfoEndDate;

	@Column(name = "STUDY_ROOM")
	private String studyRoom;

	@Column(name = "STUDY_TITLE")
	private String studyTitle;

	@Column(name = "STUDY_CONTENT")
	private String studyContent;

}
