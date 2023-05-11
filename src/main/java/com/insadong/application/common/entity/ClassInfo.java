package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_CLASS_INFO")
@SequenceGenerator(name = "CLASS_INFO_SEQ_GEN", sequenceName = "SEQ_CLASS_INFO_CODE", allocationSize = 1)
public class ClassInfo {

	@Id
	@Column(name = "CLASS_INFO_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASS_INFO_CODE")
	private Long classInfoCode;

	@ManyToOne
	@JoinColumn(name = "CLASS_CODE")
	private Class classCode;

	@ManyToOne
	@JoinColumn(name = "TEACHER")
	private Employee teacher;

	@Column(name = "CLASS_START_DATE")
	private String classStartDate;

	@Column(name = "CLASS_END_DATE")
	private String classEndDate;

	@Column(name = "CLASS_ROOM")
	private String classRoom;

	@Column(name = "CLASS_TITLE")
	private String classTitle;

	@Column(name = "CLASS_CONTENT")
	private String classContent;
}
