package com.insadong.application.eva.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.student.dto.StudentDTO;
import com.insadong.application.study.dto.StudyInfoDTO;
import lombok.Data;

import java.util.Date;

@Data
public class EvaDTO {


	private Long evaCode;
	private StudyInfoDTO StudyInfo;
	private StudentDTO student;
	private String evaWriteContent;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date evaWriteDate;
	private String evaUpdateTime;
	private String evaDeleteStatus;
	
	private Long stuCode;
	private Long studyInfoCode;

}
