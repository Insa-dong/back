package com.insadong.application.study.dto;

import com.insadong.application.employee.dto.EmployeeDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PetiteStudyDTO {

	private Long studyCode;

	private PetiteTrainingDTO training;

	private List<StudyTimeDTO> studyTimes;

	private Long studyMaxPeople;

	private EmployeeDTO studyModifier;

	private EmployeeDTO studyWriter;

	private Long studyCount;

	private String studyDeleteYn;

	private Date studyModifyDate;

	private Date studyDate;

}
