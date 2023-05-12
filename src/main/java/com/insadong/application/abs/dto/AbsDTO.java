package com.insadong.application.abs.dto;

import java.util.Date;

import com.insadong.application.employee.dto.EmployeeDTO;

import lombok.Data;

@Data
public class AbsDTO {
	
				/* 근태 */
	

	private Long absCode;			// 근태코드

	private EmployeeDTO empCode;		// 사번

	private Date absDate;			// 출근일자

	private String absStart;		// 출근시간

	private String absEnd;			// 퇴근시간

}
