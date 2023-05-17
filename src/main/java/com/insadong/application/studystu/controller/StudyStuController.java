package com.insadong.application.studystu.controller;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import com.insadong.application.study.dto.StudyStuDTO;
import com.insadong.application.studystu.service.StudyStuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class StudyStuController {

	private final StudyStuService studyStuService;

	public StudyStuController(StudyStuService studyStuService) {
		this.studyStuService = studyStuService;
	}

	// 강의 입력 보다 과정 입력
	/* Only 관리자 */
	/* 1. 수강생 강의 등록 */
	@PostMapping("/students-management/study")
	public ResponseEntity<ResponseDTO> insertStudy(@ModelAttribute StudyStuDTO studyStuDto) {

		/* 관리자만 등록하는 구문 추가해야 함 */
		studyStuService.insertStudy(studyStuDto);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수강생 강의 등록 성공"));
	}


	/* 2. 수강생 강의 수정 */

	@PutMapping("/students-management/study")
	public ResponseEntity<ResponseDTO> updateStudy(@ModelAttribute StudyStuDTO studyStuDto) {


		log.info("DTO : {} ", studyStuDto);
		studyStuService.updateStudy(studyStuDto);

		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "수강생 강의 정보 수정 성공"));

	}

	/* 3. 수강생 강의 삭제 */
	@DeleteMapping("/students-management/study/{studyCode}")
	public ResponseEntity<ResponseDTO> deleteStudy(@PathVariable Long studyCode) {

		studyStuService.deleteStudy(studyCode);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수강생 강의 삭제 성공"));
	}

	/* 4. 수강생 강의 조회 (수강생 번호) */
	@GetMapping("/students-management/study/{stuCode}")
	public ResponseEntity<ResponseDTO> selectStudyListByStudentForAdmin(
			@RequestParam(name = "page", defaultValue = "1") int page, @PathVariable Long stuCode) {

		log.info("[StudyStuController] : selectStudyListByStudentForAdmin start ==================================== ");
		log.info("[StudyStuController] : page : {}", page);

		Page<StudyStuDTO> studyStuDTOList = studyStuService.selectStudyListByStudentForAdmin(page, stuCode);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(studyStuDTOList);

		log.info("[StudyStuController] : pageInfo : {}", pageInfo);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(studyStuDTOList.getContent());

		log.info("[StudyStuController] : selectStudyListByStudentForAdmin end ==================================== ");

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

}
