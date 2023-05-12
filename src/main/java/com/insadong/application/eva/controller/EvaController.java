package com.insadong.application.eva.controller;

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
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.eva.dto.EvaDTO;
import com.insadong.application.eva.service.EvaService;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class EvaController {
	
	private final EvaService evaService;
	
	public EvaController(EvaService evaService) {
		this.evaService = evaService;
	}
	
	/* 관리자 */
	/* 관리자 평가 조회 (수강생 코드로 조회) */
	@GetMapping("/students-management/eva/{stuCode}")
	public ResponseEntity<ResponseDTO> selectEvaListByStudentForAdmin(
			@RequestParam(name="page", defaultValue="1") int page, @PathVariable Long stuCode){
		
		log.info("[EvaController] : selectEvaListByStudentForAdmin start ==================================== ");
		log.info("[EvaController] : page : {}", page);
		
		Page<EvaDTO> evaDTOList = evaService.selectEvaListByStudentForAdmin(page, stuCode);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(evaDTOList);
		
		log.info("[EvaController] : pageInfo : {}", pageInfo);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(evaDTOList.getContent());
		
		log.info("[EvaController] : selectEvaListByStudentForAdmin end ==================================== ");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	
	/* 관리자 평가 삭제 */
	@DeleteMapping("/students-management/eva/{evaCode}")
	public ResponseEntity<ResponseDTO> deleteEva(@PathVariable Long evaCode) {
		
	    evaService.deleteEva(evaCode);
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "평가 삭제 성공"));
	}
	
	
	/* 사용자 */
	/* 사용자 평가 조회 (수강생 코드로 조회) */
	@GetMapping("/students/eva/{stuCode}") 
	public ResponseEntity<ResponseDTO> selectEvaListByStudent(
			@RequestParam(name="page", defaultValue="1") int page, @PathVariable Long stuCode){
		
		log.info("[EvaController] : selectEvaListByStudent start ==================================== ");
		log.info("[EvaController] : page : {}", page);
		
		Page<EvaDTO> evaDTOList = evaService.selectEvaListByStudent(page, stuCode);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(evaDTOList);
		
		log.info("[EvaController] : pageInfo : {}", pageInfo);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(evaDTOList.getContent());
		
		log.info("[EvaController] : selectEvaListByStudent end ==================================== ");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	/* 사용자 평가 등록 */
	@PostMapping("/students/eva")
	public ResponseEntity<ResponseDTO> insertEva(@ModelAttribute EvaDTO evaDto) {

		
		evaService.insertEva(evaDto);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수강생 평가 작성 성공"));
	}
	
	/* 사용자 평가 수정 */
	@PutMapping("/students/eva")
	public ResponseEntity<ResponseDTO> updateEva(@ModelAttribute EvaDTO evaDto) {

		evaService.updateEva(evaDto);

		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "수강생 평가 수정 성공"));

	}
	

}
