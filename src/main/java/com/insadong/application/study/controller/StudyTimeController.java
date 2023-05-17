package com.insadong.application.study.controller;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.study.service.StudyTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class StudyTimeController {

	private final StudyTimeService studyTimeService;

	public StudyTimeController(StudyTimeService studyTimeService) {
		this.studyTimeService = studyTimeService;
	}

	@GetMapping("/studyTimeList/{studyCode}")
	public ResponseEntity<ResponseDTO> viewStudyTimeList(@PathVariable Long studyCode) {

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", studyTimeService.findByStudyStudyCode(studyCode)));
	}
}
