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
@Table(name="TB_CLASS_INFO")
@SequenceGenerator(name = "CLASS_INFO_SEQ_GEN", sequenceName = "SEQ_CLASS_INFO_CODE", allocationSize = 1)
public class ClassInfo {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLASS_INFO_SEQ_GENERATOR")
	private Long classInfoCode;
	
	@ManyToOne
	@JoinColumn(name="CLASS_CODE")
	private Class classCode;
	
	@ManyToOne
	@JoinColumn(name="TEACHER")
	private Employee teacher;
	
	@Column(name="CLASS_START_DATE")
	private Date classStartDate;
	
	@Column(name="CLASS_END_DATE")
	private Date classEndDate;
	
	@Column(name="CLASS_ROOM")
	private String classRoom;
	
	@Column(name="CLASS_TITLE")
	private String classTitle;
	
	@Column(name="CLASS_CONTENT")
	private String classContent;
	
}
