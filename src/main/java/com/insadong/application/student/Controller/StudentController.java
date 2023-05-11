package com.insadong.application.student.Controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import com.insadong.application.student.Service.StudentService;
import com.insadong.application.student.dto.StudentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class StudentController {
	
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
		
	}
	
	/* 관리자 */

	/* 1. 수강생 전체 조회 */
	@GetMapping("/students-management")
	public ResponseEntity<ResponseDTO> selectStudentListForAdmin(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[StudentController] : selectStudentListForAdmin start ==================================== ");
		log.info("[StudentController] : page : {}", page);
		
		Page<StudentDto> studentDtoList = studentService.selectStudentListForAdmin(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(studentDtoList);
		
		log.info("[StudentController] : pageInfo : {}", pageInfo);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(studentDtoList.getContent());
		
		log.info("[StudentController] : selectStudentListForAdmin end ==================================== ");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
		
	}
	
	/* 2. 강의별 수강생 조회 */
	
	
	/* 3. 수강생 상세 보기 */
	@GetMapping("students-management/{stuCode}")
	public ResponseEntity<ResponseDTO> selectStudentDetailForAdmin(@PathVariable Long stuCode) {
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "조회 성공", studentService.selectStudentDetailForAdmin(stuCode)));
	}
	
	/* 4. 수강생 정보 수정 */
	@PutMapping("/students")
	public ResponseEntity<ResponseDTO> updateStudent(@ModelAttribute StudentDto studentDto) {
		
		studentService.updateStudent(studentDto);
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "수강생 정보 수정 성공"));
		
	}
	
	/* 5. 수강생 등록 */
	@PostMapping("/student")
	public ResponseEntity<ResponseDTO> insertStudent(@RequestBody StudentDto studentDto) {
		
		/* 관리자만 등록하는 구문 추가해야 함 */
		studentService.insertStudent(studentDto);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수강생 등록 성공"));	
	}
	
	
	/* 6. 수강생 강의 조회 {classCode} */
	
	/* 7. 수강생 강의 등록 */
	
	/* 8. 수강생 강의 수정 */

	/* 9. 수강생 강의 삭제 */
	
	/* 10. 수강생 평가 조회 */
	
	/* 11. 수강생 평가 삭제 */
	
	/* 12. 수강생 상담 조회 */
	
	
	/* 13. 수강생 상담 삭제 */
	
	/* 사용자 */
	
	
}

