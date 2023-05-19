package com.insadong.application.study.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetiteTrainingDTO {

	private Long trainingCode;

	private String trainingTitle;

	private String trainingCount;

	private Date trainingDate;

	private Date trainingUpdate;

}
