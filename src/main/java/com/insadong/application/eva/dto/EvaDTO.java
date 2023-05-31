package com.insadong.application.eva.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.student.dto.StudentDTO;
import com.insadong.application.study.dto.StudyInfoDTO;

import lombok.Data;

@Data
public class EvaDTO {


	private Long evaCode;
	private StudyInfoDTO StudyInfo;
	private StudentDTO student;
	private String evaWriteContent;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date evaWriteDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date evaUpdateTime;
	private String evaDeleteStatus;
	

}
