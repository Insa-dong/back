package com.insadong.application.attend.dto;

import java.util.Date;

import com.insadong.application.student.dto.StudentDTO;
import com.insadong.application.study.dto.StudyDTO;

import lombok.Data;

@Data
public class AttendDTO {

	private Long attendCode;
	private StudyDTO study;
	private StudentDTO student;
	private Date attendDate;
	private String attendStatus;
	
	private Long stuCode;
	private Long studyCode;
	
}
