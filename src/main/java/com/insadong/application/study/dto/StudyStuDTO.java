package com.insadong.application.study.dto;

import com.insadong.application.student.dto.StudentDTO;

import lombok.Data;

@Data
public class StudyStuDTO {

    private StudyDTO study;
    private StudentDTO student;
    private String studyEnrollDate;
    private String studyState;
    
    // 강의 명 가져 오기 위한 변수 선언
    private String trainingTitle;

    private Long studyCode;

    public String getTrainingTitle() {
        return trainingTitle;
    }

    public void setTrainingTitle(String trainingTitle) {
        this.trainingTitle = trainingTitle;
    }

    public Long getStudyCode() {
        return studyCode;
    }

    public void setStudyCode(Long studyCode) {
        this.studyCode = studyCode;
    }
    
    private Long stuCode;
    
    public Long getStuCode() {
        return stuCode;
    }

    public void setStuCode(Long stuCode) {
        this.stuCode = stuCode;
    }
    
    private String trainingTime;
    
    public String getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(String trainingTime) {
        this.trainingTime = trainingTime;
    }
    
    private String studyTitle;
    
    public String getStudyTitle() {
    	return studyTitle;
    }
    
    public void setStudyTitle(String studyTitle) {
    		this.studyTitle = studyTitle;
    }

   private Long studyCount;
    
    public Long getStudyCount() {
    	return studyCount;
    }
    
    public void setStudyTitle(Long studyCount) {
    		this.studyCount = studyCount;
    }
    
  private Long trainingCode;
    
    public Long getTrainingCode() {
    	return trainingCode;
    }
    
    public void setTrainingCode(Long trainingCode) {
    		this.trainingCode = trainingCode;
    }
      
}