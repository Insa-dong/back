package com.insadong.application.training.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.study.dto.EmpDTO;
import lombok.Data;

import java.util.Date;

@Data
public class TrainingDTO {

	private Long trainingCode;

	private String trainingTitle;

	private String trainingQual;

	private String trainingKnow;

	private String trainingTime;

	private EmpDTO trainingWriter;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date trainingDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date trainingUpdate;

	private EmpDTO trainingModifier;

	private String trainingDeleteYn;

	private Long studyCount;
}
