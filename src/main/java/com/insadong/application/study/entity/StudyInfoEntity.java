package com.insadong.application.study.entity;

import com.insadong.application.common.entity.Eva;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_STUDY_INFO")
@SequenceGenerator(name = "STUDY_INFO_SEQ_GEN", sequenceName = "SEQ_STUDY_INFO_CODE", allocationSize = 1, initialValue = 1)
public class StudyInfoEntity {

	@Id
	@Column(name = "STUDY_INFO_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDY_INFO_SEQ_GEN")
	private Long studyInfoCode;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "STUDY")
	private StudyEntity study;

	@OneToMany(mappedBy = "StudyInfo", cascade = CascadeType.REMOVE)
	private List<Eva> eva;

	@ManyToOne
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
