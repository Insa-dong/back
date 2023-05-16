package com.insadong.application.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class StudyInfoDTO {

	private Long studyInfoCode;

	private StudyDTO study;

	private EmpDTO teacher;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private String studyStartDate;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private String studyEndDate;

	private String studyRoom;

	private String studyTitle;

	private String studyContent;


}
