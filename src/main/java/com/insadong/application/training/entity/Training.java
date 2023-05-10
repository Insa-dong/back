package com.insadong.application.training.entity;

import com.insadong.application.employee.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@SuppressWarnings("SpellCheckingInspection")
@Setter
@Getter
@Entity
@DynamicInsert
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

	@ManyToOne
	@JoinColumn(name = "TRAINING_WRITER")
	private Employee trainingWriter;

	@Column(name = "TRAINING_DATE")
	private String trainingDate;

	@Column(name = "TRAINING_UPDATE")
	private String trainingUpdate;

	@ManyToOne
	@JoinColumn(name = "TRAINING_MODIFIER")
	private Employee trainingModifier;

}
