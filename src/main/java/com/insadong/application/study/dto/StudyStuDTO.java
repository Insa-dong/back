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
    
    private Long stuCode;
    
    private String trainingTime;
    
    private Long studyCount;
 
    private Long trainingCode;
    
}