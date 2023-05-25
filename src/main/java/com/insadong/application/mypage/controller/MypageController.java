package com.insadong.application.mypage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.mypage.service.MypageService;

@RestController
@RequestMapping("/insa/v1")
public class MypageController {
	
	private final MypageService mypageService;
	
	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}
	
	@GetMapping("/mypage")
	public ResponseEntity<ResponseDTO> selectMypageInfo(@AuthenticationPrincipal EmpDTOImplUS employeeDTO){
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", mypageService.selectMypageInfo(employeeDTO.getEmpCode())));
	}

}
