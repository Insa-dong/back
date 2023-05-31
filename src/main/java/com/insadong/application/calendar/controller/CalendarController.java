package com.insadong.application.calendar.controller;

import com.insadong.application.calendar.dto.CalendarDTO;
import com.insadong.application.calendar.service.CalendarService;
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

	@GetMapping("/myPagingScheduleList")
	public ResponseEntity<ResponseDTO> viewMyPagingCal(@AuthenticationPrincipal EmpDTOImplUS empDTO, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "sort", defaultValue = "calCode") String sort) {

		log.info("sort : {} ", sort);

		Page<CalendarDTO> data = calService.viewMyPagingScheduleList(empDTO.getEmpCode(), page, sort);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(data);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(data.getContent());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}


	@PutMapping("/myScheduleUpdate")
	public ResponseEntity<ResponseDTO> updateMyCal(@RequestBody List<CalendarDTO> calendar) {
		log.info("update 시작");
		log.info("cal : {} ", calendar);
		calService.updateMyCal(calendar);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정 성공"));
	}

	@PutMapping("/myScheduleInfoUpdate")
	public ResponseEntity<ResponseDTO> updateMyCalInfo(@RequestBody CalendarDTO calendar) {
		log.info("update 시작");
		log.info("cal : {} ", calendar);
		calService.updateMyCalInfo(calendar);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정 성공"));
	}

	@PostMapping("/mySchedule")
	public ResponseEntity<ResponseDTO> registerMySchedule(@RequestBody CalendarDTO calendar, @AuthenticationPrincipal EmpDTOImplUS writer) {

		log.info("writer : {} ", writer);

		calService.registerMySchedule(calendar, writer);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "등록 성공"));
	}

	@DeleteMapping("/mySchedule")
	public ResponseEntity<ResponseDTO> deleteMySchedule(@RequestBody List<Long> codeList) {

		log.info("codeList : {} ", codeList);

		calService.deleteMySchedule(codeList);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "삭제 완료"));
	}
}