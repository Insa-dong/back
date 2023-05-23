package com.insadong.application.Tcalendar.controller;

import com.insadong.application.Tcalendar.service.CalendarService;
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.study.dto.EmpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class CalendarController {

	private final CalendarService calService;

	public CalendarController(CalendarService calService) {
		this.calService = calService;
	}

	@GetMapping("/myScheduleList")
	public ResponseEntity<ResponseDTO> viewMyCal(@AuthenticationPrincipal EmpDTO empDTO) {

		log.info("emp : {} ", empDTO);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", calService.viewMyScheduleList(empDTO.getEmpCode())));
	}
}
