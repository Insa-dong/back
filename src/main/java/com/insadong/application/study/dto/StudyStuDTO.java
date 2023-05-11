package com.insadong.application.study.dto;

import com.insadong.application.student.dto.StudentDTO;
import lombok.Data;

@Data
public class StudyStuDTO {

	private Long studyCode;

	private StudentDTO student;

	private String studyEnrollDate;

	private String studyState;

}
