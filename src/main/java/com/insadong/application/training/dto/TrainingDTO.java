package com.insadong.application.training.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.employee.dto.EmployeeDTO;
import lombok.Data;

import java.util.Date;

@Data
public class TrainingDTO {

	private Long trainingCode;

	private String trainingTitle;

	private String trainingQual;

	private String trainingKnow;

	private String trainingTime;

	private EmployeeDTO trainingWriter;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date trainingDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date trainingUpdate;

	private EmployeeDTO trainingModifier;

	private String trainingDeleteYn;

}
