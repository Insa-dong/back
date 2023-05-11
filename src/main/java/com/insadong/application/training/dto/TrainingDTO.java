package com.insadong.application.training.dto;

import com.insadong.application.employee.dto.EmployeeDTO;
import lombok.Data;

@Data
public class TrainingDTO {

	private Long trainingCode;

	private String trainingTitle;

	private String trainingQual;

	private String trainingKnow;

	private String trainingTime;

	private String trainingCount;

	private EmployeeDTO trainingWriter;

	private String trainingDate;

	private String trainingUpdate;

	private EmployeeDTO trainingModifier;
}
