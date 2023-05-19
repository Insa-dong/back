package com.insadong.application.off.dto;

import java.util.Date;

import com.insadong.application.employee.dto.EmployeeDTO;

import lombok.Data;

@Data
public class OffDTO {

	private Long signCode;					// 신청코드

	private Date offStart;					// 시작일

	private Date offEnd;					// 종료일

	private Long offDay;					// 일수

	private String signReason;				// 신청 사유

	private String signStatus;				// 승인 상태
	
	private EmployeeDTO signRequester;			// 신청자 emp_code 조인
	
	private String offDiv;					// 종류

	private EmployeeDTO signPayer;				// 결재자 emp_code 조인
	
	private Date requestDate;				// 신청일

	private String returnReason;			// 반려 사유

	private Date handleDate;				// 처리 일자
}
