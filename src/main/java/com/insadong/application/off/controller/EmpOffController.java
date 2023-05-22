package com.insadong.application.off.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public EmpOffDTO getMyOff(@AuthenticationPrincipal EmpOffDTO empOffDTO) {
		
		// 사용자의 연차 정보 가져오기
		Long empCode = empOffDTO.getEmpCode();
		EmpOffDTO myOff = empOffService.getEmpOffInfo(empCode);

		// 남은 연차 계산
		Double remainingOff = myOff.getRemainingOff();

		// 남은 연차 업데이트
        myOff.setOffCount(remainingOff);

		return myOff;
	}

}
