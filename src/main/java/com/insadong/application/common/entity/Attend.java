package com.insadong.application.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DynamicInsert
@Table(name="TB_ATTEND")
public class Attend {
	
	@Id
	@Column(name="ATTEND_CODE")
	private Long attendCode;
	
	@ManyToOne
	@JoinColumn(name="STUDY_CODE")
	private Study studyCode;
	
	@ManyToOne
	@JoinColumn(name="STU_CODE")
	private Student stuCode;
	
	
	@Column(name="ATTEND_STATUS")
	private String attendStatus;

}
