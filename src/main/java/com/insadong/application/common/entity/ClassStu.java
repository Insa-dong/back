package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_CLASS_STU")
@SequenceGenerator(name = "CLASS_STU_SEQ_GEN", sequenceName = "SEQ_CLASS_STU_CODE", allocationSize = 1)
public class ClassStu {

	@Id
	@Column(name = "CLASS_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASS_STU_CODE")
	private Long classCode;

	@ManyToOne
	@JoinColumn(name = "STU_CODE")
	private Student student;

	@Column(name = "CLASS_ENROLL_DATE")
	private String classEnrollDate;

	@Column(name = "CLASS_STATE")
	private String classState;
}
