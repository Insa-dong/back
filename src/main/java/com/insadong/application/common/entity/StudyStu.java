package com.insadong.application.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
public class StudyStu implements Serializable {

	@EmbeddedId
	private StudyStuPK studyStuPK;

	@ManyToOne
	@JoinColumn(name = "STU_CODE", insertable = false, updatable = false)
	private Student student;

	@Column(name = "STUDY_ENROLL_DATE")
	private String studyEnrollDate;

	@Column(name = "STUDY_STATE")
	private String studyState;
	
	
	  /* StudyStu entity 수정 용도의 메소드를 별도로 정의 */
    public void update(Long studyCode, String studyEnrollDate, String studyState) {
        this.studyStuPK.setStudyCode(studyCode);
        this.studyEnrollDate = studyEnrollDate;
        this.studyState = studyState;
    }
	
	
	
	
}
