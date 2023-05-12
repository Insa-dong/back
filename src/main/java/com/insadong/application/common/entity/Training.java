package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("SpellCheckingInspection")
@Setter
@Getter
@Entity
//@DynamicInsert
@Table(name = "TB_TRAINING")
@SequenceGenerator(name = "TRAINING_SEQ_GEN", sequenceName = "SEQ_TRAINING_CODE", allocationSize = 1)
public class Training {

	@Id
	@Column(name = "TRAINING_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRAINING_CODE")
	private Long trainingCode;

	@Column(name = "TRAINING_TITLE")
	private String trainingTitle;

	@Column(name = "TRAINING_QUAL")
	private String trainingQual;

	@Column(name = "TRAINING_KNOW")
	private String trainingKnow;

	@Column(name = "TRAINING_TIME")
	private String trainingTime;

	@Column(name = "TRAINING_COUNT")
	private String trainingCount;

	@ManyToOne
	@JoinColumn(name = "TRAINING_WRITER")
	private Employee trainingWriter;

	@Column(name = "TRAINING_DATE")
	private Date trainingDate;

	@Column(name = "TRAINING_UPDATE")
	private Date trainingUpdate;

	@ManyToOne
	@JoinColumn(name = "TRAINING_MODIFIER")
	private Employee trainingModifier;


	public void update(String trainingTitle, String trainingQual, String trainingKnow, String trainingTime, String trainingCount, Employee trainingWriter, Date trainingDate, Employee trainingModifier) {
		this.trainingTitle = trainingTitle;
		this.trainingQual = trainingQual;
		this.trainingKnow = trainingKnow;
		this.trainingTime = trainingTime;
		this.trainingCount = trainingCount;
		this.trainingWriter = trainingWriter;
		this.trainingDate = trainingDate;
		this.trainingModifier = trainingModifier;
	}
}
