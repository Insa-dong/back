package com.insadong.application.study.dto;

import com.insadong.application.training.dto.TrainingDTO;

import lombok.Data;

@Data
public class StudyDTO {

	private Long studyCode;

	private TrainingDTO training;

	private String studyStartDate;

	private String studyEndDate;

	private Long studyMaxPeople;

	private String studyModifier;

	private String studyDeleteYn;

}
