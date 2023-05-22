package com.insadong.application.study.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StudyDTO {

	private Long studyCode;

	private TrainingDTO training;

	private List<StudyTimeDTO> studyTimes;

	private Long studyMaxPeople;

	private EmpDTO studyModifier;

	private Long studyCount;

	private Date studyModifyDate;

	private String studyDeleteYn;

	private Date studyDate;
}
