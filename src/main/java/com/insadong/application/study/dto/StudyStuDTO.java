package com.insadong.application.study.dto;

import com.insadong.application.student.dto.StudentDTO;
import lombok.Data;

@Data
public class StudyStuDTO {

	private Long studyCode;

	private StudentDTO student;

	private String studyEnrollDate;

	private String studyState;
	
	// 강의 명 가져 오기 위한 변수 선언
    private String trainingTitle;

    // 강의 회차를 가져 오기 위한 변수 선언
    private String trainingCount;

    public String getTrainingTitle() {
        return trainingTitle;
    }

    public void setTrainingTitle(String trainingTitle) {
        this.trainingTitle = trainingTitle;
    }

    public String getTrainingCount() {
        return trainingCount;
    }

    public void setTrainingCount(String trainingCount) {
        this.trainingCount = trainingCount;
    }
    


}
