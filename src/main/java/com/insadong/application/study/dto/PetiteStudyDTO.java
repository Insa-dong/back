package com.insadong.application.study.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.employee.dto.EmployeeDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PetiteStudyDTO {

	private Long studyCode;

	private PetiteTrainingDTO training;

	private List<StudyTimeDTO> studyTimes;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date studyStartDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date studyEndDate;

	private Long studyMaxPeople;

	private EmployeeDTO studyModifier;

	private Long studyCount;

	private Date studyModifyDate;

	private String studyDeleteYn;

}
