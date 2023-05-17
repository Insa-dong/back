package com.insadong.application.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="TB_FILE")
@SequenceGenerator(name = "FILE_SEQ_GENERATOR",
sequenceName = "SEQ_FILE_CODE",
initialValue = 1, allocationSize = 1)
public class File {
	
	@Id
	@Column(name="FILE_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_SEQ_GENERATOR")
	private String fileCode;
	
	@Column(name="FILE_DIV")
	private String fileDiv;
	
	@ManyToOne
	@JoinColumn(name="NOTICE_CODE")
	private Notice noticeCode;
	
	@ManyToOne
	@JoinColumn(name="TRAINING_CODE")
	private Training trainingCode;
	
	@Column(name="ORIGIN_FILE_NAME")
	private String originFileName;
	
	@Column(name="SAVE_FILE_NAME")
	private String saveFileName;
	
	@Column(name="FILE_FATH")
	private String fileFath;
	
	@Column(name="FILE_SIZE")
	private Long fileSize;
	
	@Column(name="FILE_UPLOAD_TIME")
	private String fileUploadTime;
	
	

}
