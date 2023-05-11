package com.insadong.application.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="TB_EVA")
@SequenceGenerator(name="EVA_SEQ_GENERATOR",
sequenceName="SEQ_EVA_CODE",
initialValue=1, allocationSize=1)
public class Eva {
	
	@Id
	@Column(name="EVA_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVA_SEQ_GENERATOR")
	private Long evaCode;
	
/*
 * 	@Column()

	private 
	
	@Column()
	private   */
	
	@Column(name="EVA_WRITE_CONTENT")
	private String evaWriteContent;
	
	@Column(name="EVA_WRITE_DATE")
	private Date evaWriteDate;
	
	@Column(name="EVA_UPDATE_TIME")
	private String evaUpdateTime;
	
	@Column(name="EVA_DELETE_STATUS")
	private String evaDeleteStatus;

}
