package com.insadong.application.common.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DynamicInsert
@Table(name="TB_NOTICE")
@SequenceGenerator(name="NOTICE_SEQ_GENERATOR", sequenceName="SEQ_NOTICE_CODE", initialValue=1, allocationSize =1)
public class Notice {
	
	@Id
	@Column(name="NOTICE_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTICE_SEQ_GENERATOR")
	private Long noticeCode;
	
	@Column(name="NOTICE_TITLE")
	private String noticeTitle;
	
	@Column(name="NOTICE_CONTENT")
	private String noticeContent;
	
	@Column(name="NOTICE_WRITE_DATE")
	private Date noticeWriteDate;
	
	@ManyToOne
	@JoinColumn(name="NOTICE_WRITER")
	private Employee noticeWriter;
	
	@Column(name="NOTICE_MODIFY_DATE")
	private Date noticeModifyDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="NOTICE_CODE")
	private List<File> fileList;
}

