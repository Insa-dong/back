package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("SpellCheckingInspection")
@Setter
@Getter
@Entity
@ToString
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_TRAINING")
@SequenceGenerator(name = "TRAINING_SEQ_GEN", sequenceName = "SEQ_TRAINING_CODE", allocationSize = 1)
public class Training {

	@Id
	@Column(name = "TRAINING_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAINING_SEQ_GEN")
	private Long trainingCode;

	@Column(name = "TRAINING_TITLE")
	private String trainingTitle;

	@Column(name = "TRAINING_QUAL")
	private String trainingQual;

	@Column(name = "TRAINING_KNOW")
	private String trainingKnow;

	@Column(name = "TRAINING_TIME")
	private String trainingTime;

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

	@Column(name = "TRAINING_DELETE_YN")
	private String trainingDeleteYn;


	public void update(String trainingTitle, String trainingQual, String trainingKnow, String trainingTime, Employee trainingWriter, Date trainingDate, Employee trainingModifier, String trainingDeleteYn) {
		this.trainingTitle = trainingTitle;
		this.trainingQual = trainingQual;
		this.trainingKnow = trainingKnow;
		this.trainingTime = trainingTime;
		this.trainingWriter = trainingWriter;
		this.trainingDate = trainingDate;
		this.trainingModifier = trainingModifier;
		this.trainingDeleteYn = trainingDeleteYn;
		this.trainingUpdate = new Date();
	}
}
