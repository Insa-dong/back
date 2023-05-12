package com.insadong.application.advice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.advice.dto.AdviceDTO;
import com.insadong.application.advice.service.AdviceService;
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class AdviceController {
	
	private final AdviceService adviceService;
	
	public AdviceController(AdviceService adviceService) {
		this.adviceService = adviceService;
	}
	
	/* 관리자 */
	
	/* 1. 수강생 상담 조회 (수강생 번호) */
	@GetMapping("/students-management/advice/{stuCode}") 
	public ResponseEntity<ResponseDTO> selectAdivceListByStudentForAdmin(
			@RequestParam(name="page", defaultValue="1") int page, @PathVariable Long stuCode){
		
		log.info("[AdviceController] : selectAdivceListByStudentForAdmin start ==================================== ");
		log.info("[AdviceController] : page : {}", page);
		
		Page<AdviceDTO> adviceDTOList = adviceService.selectAdviceListByStudentForAdmin(page, stuCode);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(adviceDTOList);
		
		log.info("[AdviceController] : pageInfo : {}", pageInfo);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(adviceDTOList.getContent());
		
		log.info("[ProductController] : selectAdivceListByStudentForAdmin end ==================================== ");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	
	/* 2. 수강생 상담 삭제 */
	@DeleteMapping("/students-management/advice/{adviceLogCode}")
	public ResponseEntity<ResponseDTO> deleteAdvice(@PathVariable Long adviceLogCode) {
	    adviceService.deleteAdvice(adviceLogCode);
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상담일지 삭제 성공"));
	}

	
	/* 사용자 */
	
	/* 1. 수강생 상담 조회 */
	@GetMapping("/students/advice/{stuCode}") 
	public ResponseEntity<ResponseDTO> selectAdivceListByStudent(
			@RequestParam(name="page", defaultValue="1") int page, @PathVariable Long stuCode){
		
		log.info("[AdviceController] : selectAdivceListByStudent start ==================================== ");
		log.info("[AdviceController] : page : {}", page);
		
		Page<AdviceDTO> adviceDTOList = adviceService.selectAdviceListByStudent(page, stuCode);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(adviceDTOList);
		
		log.info("[AdviceController] : pageInfo : {}", pageInfo);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(adviceDTOList.getContent());
		
		log.info("[ProductController] : selectAdivceListByStudent end ==================================== ");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	
	/* 2. 수강생 상담 등록 */
	@PostMapping("/students/advice")
	public ResponseEntity<ResponseDTO> insertAdvice(@ModelAttribute AdviceDTO adviceDto) {

		/* 관리자만 등록하는 구문 추가해야 함 */
		adviceService.insertAdvice(adviceDto);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수강생 상담일지 작성 성공"));
	}
	
	
	/* 3. 수강생 상담 수정 */
	@PutMapping("/students/advice")
	public ResponseEntity<ResponseDTO> updateAdvice(@ModelAttribute AdviceDTO adviceDto) {

		adviceService.updateAdvice(adviceDto);

		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "수강생 상담 수정 성공"));

	}
	
}
