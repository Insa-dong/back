package com.insadong.application.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StudyInfoDTO {

	private Long studyInfoCode;

	private StudyDTO study;

	private EmpDTO teacher;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date studyInfoStartDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date studyInfoEndDate;

	private String studyRoom;

	private String studyTitle;

	private String studyContent;


}
