package com.insadong.application.study.controller;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import com.insadong.application.study.dto.StudyDTO;
import com.insadong.application.study.service.StudyInfoService;
import com.insadong.application.study.service.StudyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class StudyController {

	private final StudyService studyService;
	private final StudyInfoService studyInfoService;

	public StudyController(StudyService studyService, StudyInfoService studyInfoService) {
		this.studyService = studyService;
		this.studyInfoService = studyInfoService;
	}

	@GetMapping("/studyList")
	public ResponseEntity<ResponseDTO> viewStudyList(@RequestParam(name = "page", defaultValue = "1") int page) {

		Page<StudyDTO> studyList = studyService.viewStudyList(page);
		PagingButtonInfo pagingButtonInfo = Pagenation.getPagingButtonInfo(studyList);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pagingButtonInfo);
		responseDTOWithPaging.setData(studyList.getContent());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
}
