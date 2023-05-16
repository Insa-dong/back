package com.insadong.application.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class StudyDTO {

	private Long studyCode;

	private TrainingDTO training;

	private List<StudyTimeDTO> studyTimes;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private String studyStartDate;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private String studyEndDate;

	private Long studyMaxPeople;

	private String studyModifier;

	private String studyDeleteYn;


}
