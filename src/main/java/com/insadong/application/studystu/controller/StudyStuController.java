package com.insadong.application.studystu.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.attend.dto.AttendDTO;
import com.insadong.application.attend.dto.StudyStuAttendDTO;
import com.insadong.application.attend.repository.AttendRepository;
import com.insadong.application.attend.service.AttendService;
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.common.entity.Attend;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import com.insadong.application.study.dto.StudyStuDTO;
import com.insadong.application.study.repository.StudyRepository;
import com.insadong.application.studystu.service.StudyStuService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class StudyStuController {

	private final StudyStuService studyStuService;
	private final AttendService attendService;
	private final AttendRepository attendRepository;
	private final StudyRepository studyRepository;

	public StudyStuController(StudyStuService studyStuService, AttendService attendService, AttendRepository attendRepository, StudyRepository studyRepository) {
		this.studyStuService = studyStuService;
		this.attendService = attendService;
		this.attendRepository = attendRepository;
		this.studyRepository = studyRepository;
	}
	
	/* Only 관리자 */
	
	/* 1. 수강생 강의 등록 */
	@PostMapping("/students-management/study")
	public ResponseEntity<ResponseDTO> insertStudy(@RequestBody StudyStuDTO studyStuDto) {

	    /* 관리자만 등록하는 구문 추가해야 함 */
	    studyStuService.insertStudy(studyStuDto);

	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수강생 강의 등록 성공"));
	}


	/* 2. 수강생 강의 수정 */
	@PutMapping("/students-management/study")
	public ResponseEntity<ResponseDTO> updateStudy(@RequestBody StudyStuDTO studyStuDto) {

	    log.info("DTO : {} ", studyStuDto);
	    studyStuService.updateStudy(studyStuDto);

	    return ResponseEntity
	            .ok()
	            .body(new ResponseDTO(HttpStatus.OK, "수강생 강의 정보 수정 성공"));

	}
	

	/* 3. 수강생 강의 삭제 */
//	@DeleteMapping("/students-management/study/{studyCode}")
//	public ResponseEntity<ResponseDTO> deleteStudy(@PathVariable Long studyCode) {
//	    studyStuService.deleteStudy(studyCode);
//	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수강생 강의 삭제 성공"));
//	}
//	
	
//	/* 3. 수강생 강의 삭제 */
//	@DeleteMapping("/students-management/study/{studyCode}")
//	public ResponseEntity<ResponseDTO> deleteStudy(@PathVariable Long studyCode) {
//	    List<Attend> attends = attendRepository.findByStudyStudyCode(studyCode);
//	    attendRepository.deleteAll(attends);
//
//	    // 수강생 강의 삭제
//	    studyStuService.deleteStudy(studyCode);
//
//	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수강생 강의 삭제 성공"));
//	}



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
	
	/* 5. 수강생 과정 목록 조회 */
	@GetMapping("/students-management/studylist")
	public ResponseEntity<ResponseDTO> selectAllStudyInfo() {
	    log.info("[StudyStuController] : selectAllStudyInfo start ==================================== ");
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", studyStuService.selectAllStudyInfo()));
	}
	
	/* 사용자 */
	/* 1. 사용자(강사) 강의 별 수강생 조회 */
	@GetMapping("/students/study/{studyCode}")
	public ResponseEntity<ResponseDTO> selectStudentListByStudy(
			@RequestParam(name = "page", defaultValue = "1") int page, @PathVariable Long studyCode) {

		log.info("[StudyStuController] : selectStudentListByStudy start ==================================== ");
		log.info("[StudyStuController] : page : {}", page);

		Page<StudyStuDTO> studyStuDTOList = studyStuService.selectStudentListByStudy(page, studyCode);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(studyStuDTOList);

		log.info("[StudyStuController] : pageInfo : {}", pageInfo);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(studyStuDTOList.getContent());

		log.info("[StudyStuController] : selectStudentListByStudy end ==================================== ");

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	/* 강의별 수강생 & 출결 조회 */
	@GetMapping("/studyAndAttend/{studyCode}")
	public ResponseEntity<ResponseDTO> selectStudentAndAttendListByStudy(
	        @RequestParam(name = "page", defaultValue = "1") int page, @PathVariable Long studyCode) {

	    log.info("[StudyStuAttendController] : selectStudentAndAttendListByStudy start ==================================== ");
	    log.info("[StudyStuAttendController] : page : {}", page);

	    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("study.studyCode").descending());
	    
	    Page<StudyStuDTO> studentList = studyStuService.selectStudentListByStudy(page, studyCode);
	    Page<AttendDTO> attendList = attendService.selectAttendListByStudent(page, studyCode);

	    // 페이징 정보 생성
	    PagingButtonInfo studentPageInfo = Pagenation.getPagingButtonInfo(studentList);
	    PagingButtonInfo attendPageInfo = Pagenation.getPagingButtonInfo(attendList);

	    // 응답 데이터 생성
	    StudyStuAttendDTO result = new StudyStuAttendDTO();
	    result.setStudentList(studentList.getContent());
	    result.setAttendList(attendList.getContent());

	    // 응답 객체 생성 및 데이터 설정
	    ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
	    responseDTOWithPaging.setPageInfo(studentPageInfo);
	    responseDTOWithPaging.setData(result);

	    log.info("[StudyStuAttendController] : selectStudentAndAttendListByStudy end ==================================== ");

	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	


	
}
