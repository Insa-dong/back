package com.insadong.application.attend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.insadong.application.attend.service.AttendService;
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import com.insadong.application.study.dto.StudyStuDTO;
import com.insadong.application.studystu.service.StudyStuService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/insa/v1")
public class AttendController {
	
	private final AttendService attendService;
	private final StudyStuService studyStuService;
	
	public AttendController(AttendService attendService, StudyStuService studyStuService) {
		this.attendService = attendService;
		this.studyStuService = studyStuService;
	}
	
	
	/* 수강생 출결 조회 */
//	@GetMapping("/students/attend/{studyCode}")
//	public ResponseEntity<ResponseDTO> selectAttendListByStudent(
//			@RequestParam(name="page", defaultValue="1") int page, @PathVariable Long studyCode) {
//		
//		log.info("[AttendController] : selectAttendListByStudent start ==================================== ");
//		log.info("[AttendController] : page : {}", page);
//		
//		Page<AttendDTO> attendDTOList = attendService.selectAttendListByStudent(page, studyCode);
//		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(attendDTOList);
//		
//		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
//		responseDTOWithPaging.setPageInfo(pageInfo);
//		responseDTOWithPaging.setData(attendDTOList.getContent());
//		
//		log.info("[AttendController] : selectAttendListByStudent end ==================================== ");
//		
//		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
//	}
	
	/* 수강생 출결 상세 조회 */
	@GetMapping("/students/attendDetail/{stuCode}")
	public ResponseEntity<ResponseDTO> selectAttendListDetailByStudent(
			@RequestParam(name="page", defaultValue="1") int page, @PathVariable Long stuCode) {
		
		log.info("[AttendController] : selectAttendListDetailByStudent start ==================================== ");
		log.info("[AttendController] : page : {}", page);
		
		Page<AttendDTO> attendDTOList = attendService.selectAttendListDetailByStudent(page, stuCode);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(attendDTOList);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(attendDTOList.getContent());
		
		log.info("[AttendController] : selectAttendListDetailByStudent end ==================================== ");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	/* 수강생 출결 등록 */
	@PostMapping("/students/attend")
	public ResponseEntity<ResponseDTO> insertAttend(@RequestBody AttendDTO attendDto) {

	    attendService.insertAttend(attendDto);
	    
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "출결 등록 성공"));
	}
	
	
	/* 수강생 출결 수정 */
	@PutMapping("/students/attend")
	public ResponseEntity<ResponseDTO> updateAttend(@RequestBody AttendDTO attendDto) {
		
		attendService.updateAttend(attendDto);
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "수강생 출결 수정 성공"));
		
	}
	
	/* 수강생 출결 삭제 */
	@DeleteMapping("/students/attend/{attendCode}")
	public ResponseEntity<ResponseDTO> deleteAttend(@PathVariable Long attendCode) {
	    attendService.deleteAttend(attendCode);
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상담일지 삭제 성공"));
	}
	
	/* 수강생 날짜 별 조회 (검색) */
//	@GetMapping("/student-attends/{attendDate}")
//	public ResponseEntity<ResponseDTO> selectStudentAttends(
//			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate attendDate,
//	        @RequestParam(name="page", defaultValue="1") int page) {
//
//	    Page<AttendDTO> attendDtoList = attendService.selectStudentAttends(attendDate, page);
//	    
//	    PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(attendDtoList);
//	    
//	    ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
//	    responseDTOWithPaging.setPageInfo(pageInfo);
//	    responseDTOWithPaging.setData(attendDtoList.getContent()); 
//	    
//	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
//	}
	
	/* 수강생 날짜 별 검색(studyCode)/(attendDate) 기준*/
	@GetMapping("/studyAndAttend/{studyCode}/student-attends/{attendDate}")
	public ResponseEntity<ResponseDTO> selectStudentAttendsByDate(
	        @PathVariable Long studyCode,
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate attendDate,
	        @RequestParam(name="page", defaultValue="1") int page) {

		Page<StudyStuDTO> studentList = studyStuService.selectStudentListByStudy(page, studyCode);
		List<AttendDTO> attendDtoList = attendService.selectStudentAttendsByDate(attendDate);

		// 페이징 정보 생성
		PagingButtonInfo studentPageInfo = Pagenation.getPagingButtonInfo(studentList);

		// 응답 데이터 생성
		StudyStuAttendDTO result = new StudyStuAttendDTO();
		result.setStudentList(studentList.getContent());
		result.setAttendList(attendDtoList);

		// 응답 객체 생성 및 데이터 설정
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(studentPageInfo);
		responseDTOWithPaging.setData(result);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}


			
}
