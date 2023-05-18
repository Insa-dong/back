package com.insadong.application.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class StudyStuPK implements Serializable {

	@Column(name="STUDY_CODE")
	private Long studyCode;
	
	@Column(name="STU_CODE")
	private Long stuCode;
	
	
	  public StudyStuPK() {
		  
	   }

	    public StudyStuPK(Long studyCode, Long stuCode) {
	        this.studyCode = studyCode;
	        this.stuCode = stuCode;
	    }

}
