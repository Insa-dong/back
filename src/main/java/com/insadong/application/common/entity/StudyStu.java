package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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

	@ManyToOne
	@JoinColumn(name = "STUDY_CODE", insertable = false, updatable = false)
	private Study study;

	@Column(name = "STUDY_ENROLL_DATE")
	private String studyEnrollDate;

	@Column(name = "STUDY_STATE")
	private String studyState;

	/* StudyStu entity 수정 용도의 메소드를 별도로 정의 */
	public void update(String studyEnrollDate, String studyState) {

		this.studyEnrollDate = studyEnrollDate;
		this.studyState = studyState;
	}


}
