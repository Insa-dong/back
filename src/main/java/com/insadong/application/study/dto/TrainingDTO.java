package com.insadong.application.study.dto;

import lombok.Data;

@Data
public class TrainingDTO {

	private Long trainingCode;

	private String trainingTitle;

	private String trainingQual;

	private String trainingKnow;

	private String trainingTime;

	private String trainingCount;

	private EmpDTO trainingWriter;

}
