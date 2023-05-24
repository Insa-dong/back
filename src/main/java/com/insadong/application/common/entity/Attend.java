package com.insadong.application.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DynamicInsert
@Table(name="TB_ATTEND")
@SequenceGenerator(name="ATTEND_SEQ_GENERATOR",
sequenceName="SEQ_ATTEND_CODE",
initialValue=1, allocationSize=1)
public class Attend {
	
	@Id
	@Column(name="ATTEND_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATTEND_SEQ_GENERATOR")
	private Long attendCode;
	
	@ManyToOne
	@JoinColumn(name="STUDY_CODE")
	private Study study;
	
	@ManyToOne
	@JoinColumn(name="STU_CODE")
	private Student student;
	
	@Column(name="ATTEND_DATE")
	private Date attendDate;
		
	@Column(name="ATTEND_STATUS")
	private String attendStatus;
	
	public void update(Date attendDate, String attendStatus) {
		
		this.attendDate = attendDate;
		this.attendStatus = attendStatus;
	}

}