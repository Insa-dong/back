package com.insadong.application.off.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.off.dto.EmpOffDTO;
import com.insadong.application.off.service.EmpOffService;

@RestController
@RequestMapping("/off")
public class EmpOffController {
	
	// 총 연차 15개로 고정
    public static final Long TOTAL_OFF_LEAVE = 15L;
	private final EmpOffService empOffService;

	public EmpOffController(EmpOffService empOffService) {
		this.empOffService = empOffService;

	}

	/* 2. 내 연차 현황 조회 */

	   @GetMapping("/myOff")
	    public EmpOffDTO getMyOff(@AuthenticationPrincipal EmpDTOImplUS loggedInUser) {
	        Long empCode = loggedInUser.getEmpCode();

	        // 사용자의 연차 정보 가져오기
	        EmpOffDTO empOffDTO = empOffService.getEmpOffInfo(empCode);

	        // 사용한 연차 계산
	        Double usedOff = empOffService.calculateUsedOff(empOffDTO);

	        // 총 연차는 15로 고정
	        Double totalOff = 15.0;

	        // 남은 연차 계산
	        Double remainingOff = Math.max(totalOff - usedOff, 0);

	        // offCount에 남은 연차 저장
	        empOffDTO.setOffCount(remainingOff);

	        // 값 설정
	        empOffDTO.setUsedOff(usedOff);

	        // 사용자 정보 업데이트
	        empOffService.updateOffCount(empCode, remainingOff);

	        return empOffDTO;
	    }
	}


