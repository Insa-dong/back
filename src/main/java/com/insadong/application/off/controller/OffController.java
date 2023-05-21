package com.insadong.application.off.controller;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.insadong.application.off.dto.OffDTO;
import com.insadong.application.off.service.OffService;


@RestController
@RequestMapping("/off")
public class OffController {
	
	// 총 연차 15개로 고정
	public static final Long TOTAL_OFF_LEAVE = 15L;
	private final OffService offService;
	
	public OffController(OffService offService) {
		this.offService = offService;
	}
		
	/*1. 연차 신청*/
    @PostMapping("/apply")
    public ResponseEntity<String> applyOff(@RequestBody OffDTO offDTO) {
        offService.applyOff(offDTO);
        return ResponseEntity.ok("신청 완료");
    }

		
		


}
