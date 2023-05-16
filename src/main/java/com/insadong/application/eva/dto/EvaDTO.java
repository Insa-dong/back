package com.insadong.application.eva.dto;

import com.insadong.application.student.dto.StudentDTO;
import com.insadong.application.studyInfo.dto.StudyInfoDTO;
import lombok.Data;

import java.util.Date;

@Data
public class EvaDTO {


	private Long evaCode;
	private StudyInfoDTO StudyInfo;
	private StudentDTO student;
	private String evaWriteContent;
	private Date evaWriteDate;
	private String evaUpdateTime;
	private String evaDeleteStatus;

}
