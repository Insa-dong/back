package com.insadong.application.off.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.off.dto.EmpOffDTO;
import com.insadong.application.off.service.EmpOffService;

@RestController
@RequestMapping("/off")
public class EmpOffController {
	

	private final EmpOffService empOffService;

	public EmpOffController(EmpOffService empOffService) {
		this.empOffService = empOffService;

	}

	/* 2. 내 연차 현황 조회 */

	   @GetMapping("/myOff")
	    public ResponseEntity<ResponseDTO> getMyOff(@AuthenticationPrincipal EmpDTOImplUS emp) {

		   EmpOffDTO empOffDTO = empOffService.showMyOff(emp.getEmpCode());

	        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "등록 성공", empOffDTO));
	    }
	}


