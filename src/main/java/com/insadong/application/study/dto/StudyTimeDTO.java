package com.insadong.application.study.dto;

import lombok.Data;

@Data
public class StudyTimeDTO {

	private Long studyTimeCode;

	private StudyDTO study;

	private String studyDate;

	private String studyStartTime;

	private String studyEndTime;


}
