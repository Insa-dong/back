package com.insadong.application.off.dto;

import java.time.LocalDate;
import java.util.Date;

import com.insadong.application.employee.dto.EmployeeDTO;

import lombok.Data;

@Data
public class OffDTO {

	private Long signCode;							// 신청코드

	private LocalDate offStart;						// 시작일

	private LocalDate offEnd;						// 종료일

	private Double offDay;							// 일수

	private String signReason;						// 신청 사유

	private String signStatus ="대기";				// 승인 상태
	
	private EmployeeDTO signRequester;				// 신청자 emp_code 조인
	
	private String offDiv;							// 종류

	private EmployeeDTO signPayer;					// 결재자 emp_code 조인
	
	private Date requestDate;						// 신청일

	private String returnReason;					// 반려 사유

	private Date handleDate;						// 처리 일자
		

}
