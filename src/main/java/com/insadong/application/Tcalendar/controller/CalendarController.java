package com.insadong.application.Tcalendar.controller;

import com.insadong.application.Tcalendar.dto.CalendarDTO;
import com.insadong.application.Tcalendar.service.CalendarService;
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class CalendarController {

	private final CalendarService calService;

	public CalendarController(CalendarService calService) {
		this.calService = calService;
	}

	@GetMapping("/myScheduleList")
	public ResponseEntity<ResponseDTO> viewMyCal(@AuthenticationPrincipal EmpDTOImplUS empDTO) {

		List<CalendarDTO> data = calService.viewMyScheduleList(empDTO.getEmpCode());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", data));
	}

	@PutMapping("/myScheduleUpdate")
	public ResponseEntity<ResponseDTO> updateMyCal(@RequestBody List<CalendarDTO> calendar) {

		log.info("cal : {} ", calendar);
		calService.updateMyCal(calendar);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정 성공"));
	}
}
