package com.insadong.application.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class StudyTimeDTO {

	private Long studyTimeCode;

	private String studyDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private String studyStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private String studyEndTime;

}
