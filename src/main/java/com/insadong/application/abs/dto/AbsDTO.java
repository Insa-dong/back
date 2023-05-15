package com.insadong.application.abs.dto;

import java.time.LocalDate;
import java.util.Date;

import com.insadong.application.employee.dto.EmployeeDTO;

import lombok.Data;

@Data
public class AbsDTO {
	
				/* 근태 */
	

	private Long absCode;			// 근태코드

	private EmployeeDTO empCode;		// 사번

	private LocalDate absDate;			// 출근일자

	private Date absStart;		// 출근시간

	private Date absEnd;			// 퇴근시간



}
