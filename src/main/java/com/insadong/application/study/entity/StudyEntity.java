package com.insadong.application.study.entity;

import com.insadong.application.common.entity.StudyTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@DynamicUpdate
@DynamicInsert
@Table(name = "TB_STUDY")
@SequenceGenerator(name = "STUDY_SEQ_GEN", sequenceName = "SEQ_STUDY_CODE", allocationSize = 1)
public class StudyEntity {

	@Id
	@Column(name = "STUDY_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDY_SEQ_GEN")
	private Long studyCode;

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "STUDY_CODE")
	private List<StudyTime> studyTimes;

	@ManyToOne
	@JoinColumn(name = "TRAINING_CODE")
	private TrainingEntity training;

	@Column(name = "STUDY_MAX_PEOPLE")
	private Long studyMaxPeople;

	@Column(name = "STUDY_DELETE_YN")
	private String studyDeleteYn;

	@ManyToOne
	@JoinColumn(name = "STUDY_MODIFIER")
	private EmpEntity studyModifier;

	@ManyToOne
	@JoinColumn(name = "STUDY_WRITER")
	private EmpEntity studyWriter;

	@Column(name = "STUDY_COUNT")
	private Long studyCount;

	@Column(name = "STUDY_MODIFY_DATE")
	private Date studyModifyDate;

	@Column(name = "STUDY_DATE")
	private Date studyDate;

}
