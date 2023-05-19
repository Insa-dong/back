package com.insadong.application.study.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@ToString
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_TRAINING")
public class trainingEntity {

	@Id
	@Column(name = "TRAINING_CODE")
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
	private empEntity trainingWriter;

	@Column(name = "TRAINING_DATE")
	private Date trainingDate;

	@Column(name = "TRAINING_UPDATE")
	private Date trainingUpdate;

	@Column(name = "TRAINING_DELETE_YN")
	private String trainingDeleteYn;

}
