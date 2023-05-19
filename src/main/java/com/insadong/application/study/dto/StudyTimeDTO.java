package com.insadong.application.study.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudyTimeDTO {

	private Long studyTimeCode;

	private Long studyCode;

	private String studyDate;

	private Date studyStartTime;

	private Date studyEndTime;

}
