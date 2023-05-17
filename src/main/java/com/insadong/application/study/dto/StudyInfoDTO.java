package com.insadong.application.study.dto;

import com.insadong.application.employee.dto.EmployeeDTO;
import lombok.Data;

@Data
public class StudyInfoDTO {

	private Long studyInfoCode;

	private StudyDTO study;

	private EmployeeDTO teacher;

	private String studyStartDate;

	private String studyEndDate;

	private String studyRoom;

	private String studyTitle;

	private String studyContent;


}
