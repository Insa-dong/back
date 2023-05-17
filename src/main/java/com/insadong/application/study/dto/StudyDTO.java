package com.insadong.application.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class StudyDTO {

	private Long studyCode;

	private TrainingDTO training;

	private List<StudyTimeDTO> studyTimes;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date studyStartDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date studyEndDate;

	private Long studyMaxPeople;

	private EmpDTO studyModifier;

	private Date studyModifyDate;

	private String studyDeleteYn;


}
