package com.insadong.application.attend.dto;

import java.util.List;

import com.insadong.application.study.dto.StudyStuDTO;

import lombok.Data;

@Data
public class StudyStuAttendDTO {
	
	private List<StudyStuDTO> studentList;
	private List<AttendDTO> attendList;
    

}
