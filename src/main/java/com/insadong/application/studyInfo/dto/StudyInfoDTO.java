package com.insadong.application.studyInfo.dto;

import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.study.dto.StudyDTO;

import lombok.Data;

@Data
public class StudyInfoDTO {

	private Long studyInfoCode;

	private StudyDTO studyCode;

	private EmployeeDTO teacher;

	private String studyStartDate;

	private String studyEndDate;

	private String studyRoom;

	private String studyTitle;

	private String studyContent;


}