package com.insadong.application.study.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@ToString
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_TRAINING")
public class TrainingEntity {

	@Id
	@Column(name = "TRAINING_CODE")
	private Long trainingCode;

	@Column(name = "TRAINING_TITLE")
	private String trainingTitle;

}
