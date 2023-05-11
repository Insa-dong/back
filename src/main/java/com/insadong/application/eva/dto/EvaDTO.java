package com.insadong.application.eva.dto;

import java.util.Date;

import com.insadong.application.common.entity.Student;
import com.insadong.application.common.entity.StudyInfo;

import lombok.Data;

@Data
public class EvaDTO {

	
	private Long evaCode;
	private StudyInfo StudyInfo;
	private Student student;
	private String evaWriteContent;
	private Date evaWriteDate;
	private String evaUpdateTime;
	private String evaDeleteStatus;
	
}
