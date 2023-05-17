package com.insadong.application.study.controller;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import com.insadong.application.study.dto.PetiteStudyInfoDTO;
import com.insadong.application.study.dto.StudyInfoDTO;
import com.insadong.application.study.service.StudyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("insa/v1")
public class StudyInfoController {

	private final StudyInfoService studyInfoService;

	public StudyInfoController(StudyInfoService studyInfoService) {
		this.studyInfoService = studyInfoService;
	}

	@GetMapping("/studyInfoList")
	public ResponseEntity<ResponseDTO> viewStudyList(@RequestParam(name = "page", defaultValue = "1") int page) {

		Page<StudyInfoDTO> data = studyInfoService.viewStudyInfoList(page);
		PagingButtonInfo pagingButtonInfo = Pagenation.getPagingButtonInfo(data);

		log.info("DTO : {} ", data);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pagingButtonInfo);
		responseDTOWithPaging.setData(data.getContent());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

	@GetMapping("/PetiteStudyInfo/{studyInfoCode}")
	public ResponseEntity<ResponseDTO> viewStudyInfo(@PathVariable Long studyInfoCode) {

		PetiteStudyInfoDTO data = studyInfoService.viewPetiteStudyInfo(studyInfoCode);

		log.info("data : {} ", data);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", data));
	}
}