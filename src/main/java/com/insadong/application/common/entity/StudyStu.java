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
@Table(name = "TB_STUDY_STU")
@SequenceGenerator(name = "STUDY_STU_SEQ_GEN", sequenceName = "SEQ_STUDY_STU_CODE", allocationSize = 1)
public class StudyStu {

	@Id
	@Column(name = "STUDY_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDY_STU_CODE")
	private Long studyCode;

	@ManyToOne
	@JoinColumn(name = "STU_CODE")
	private Student student;

	@Column(name = "STUDY_ENROLL_DATE")
	private String studyEnrollDate;

	@Column(name = "STUDY_STATE")
	private String studyState;
	
	/* StudyStu entity 수정 용도의 메소드를 별도로 정의 */
	public void update(String studyEnrollDate, String State) {
		
		this.studyEnrollDate = studyEnrollDate;
		this.studyState = studyState;
			
		
	}
	
	
	
	
}
