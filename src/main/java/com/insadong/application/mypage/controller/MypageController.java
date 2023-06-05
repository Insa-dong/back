package com.insadong.application.mypage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.mypage.service.MypageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class MypageController {

	private final MypageService mypageService;

	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}

	/* 내정보 조회 */
	@GetMapping("/mypage")
	public ResponseEntity<ResponseDTO> selectMypageInfo(@AuthenticationPrincipal EmpDTOImplUS employeeDTO) {

		return ResponseEntity.ok().body(
				new ResponseDTO(HttpStatus.OK, "조회 성공", mypageService.selectMypageInfo(employeeDTO.getEmpCode())));
	}

	/* 비밀번호 변경 */
	@PutMapping("/pwdmodify")
	public ResponseEntity<ResponseDTO> modifyPwd(@RequestBody EmployeeDTO empDTO,
			@AuthenticationPrincipal EmpDTOImplUS employeeDTO) {

		mypageService.modifyPwd(empDTO, employeeDTO.getEmpCode());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "비밀번호 변경 성공"));
	}

	/* 개인정보 변경 */
	@PutMapping("/privacymodify")
	public ResponseEntity<ResponseDTO> modifyPrivacy(@RequestBody EmployeeDTO empDTO,
			@AuthenticationPrincipal EmpDTOImplUS employeeDTO) {

		mypageService.modifyPrivacy(empDTO, employeeDTO.getEmpCode());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "개인정보 변경 성공"));
	}
}
