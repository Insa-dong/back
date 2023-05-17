package com.insadong.application.training.controller;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import com.insadong.application.training.dto.TrainingDTO;
import com.insadong.application.training.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class TrainingController {

	private final TrainingService trainingService;

	public TrainingController(TrainingService trainingService) {
		this.trainingService = trainingService;
	}

	@GetMapping("/trainingTitleList")
	public ResponseEntity<ResponseDTO> viewTrainingTitleList() {

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", trainingService.viewTrainingTitleList()));
	}

	@GetMapping("/trainingList")
	public ResponseEntity<ResponseDTO> viewTrainingList(@RequestParam(name = "page", defaultValue = "1") int page) {

		Page<TrainingDTO> trainingList = trainingService.viewTrainingList(page);
		PagingButtonInfo pagingButtonInfo = Pagenation.getPagingButtonInfo(trainingList);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pagingButtonInfo);
		responseDTOWithPaging.setData(trainingList.getContent());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

	@GetMapping("/training/{trainingCode}")
	public ResponseEntity<ResponseDTO> viewTraining(@PathVariable Long trainingCode) {

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", trainingService.viewTraining(trainingCode)));
	}

	@PutMapping("/training")
	public ResponseEntity<ResponseDTO> modifyTraining(@RequestBody TrainingDTO trainingDTO, @AuthenticationPrincipal EmployeeDTO employeeDTO) {

		log.info("trainingDTO : {} ", trainingDTO);
		log.info("employeeDTO : {} ", employeeDTO);
		trainingService.updateTraining(trainingDTO, employeeDTO.getEmpCode());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정 성공"));
	}

	@PutMapping("/training/delete/{trainingCode}")
	public ResponseEntity<ResponseDTO> updateTrainingDelete(@PathVariable Long trainingCode, @AuthenticationPrincipal EmployeeDTO employeeDTO) {

		log.info("trainingCode : {} ", trainingCode);
		log.info("employeeDTO : {} ", employeeDTO);
		trainingService.updateDeleteYN(trainingCode, employeeDTO.getEmpCode());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "삭제 성공"));
	}

	@PostMapping("/training")
	public ResponseEntity<ResponseDTO> insertTraining(@RequestBody TrainingDTO trainingDTO) {

		log.info("DTO : {} ", trainingDTO);
		long empCode = 1000;
		trainingService.insertTraining(trainingDTO, empCode);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "등록이 완료되었습니다. 메인 페이지로 이동합니다."));
	}

	@GetMapping("/trainingList/search")
	public ResponseEntity<ResponseDTO> searchTrainingByTitle(@RequestParam(name = "search") String trainingTitle,
	                                                         @RequestParam(name = "page", defaultValue = "1") int page) {

		log.info("검색어 : {} ", trainingTitle);

		Page<TrainingDTO> trainingList = trainingService.selectTrainingListByTrainingTitle(trainingTitle, page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(trainingList);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(trainingList.getContent());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

	@GetMapping("/trainingList/searchCount")
	public ResponseEntity<ResponseDTO> searchTrainingByCount(@RequestParam(name = "searchCount") String trainingCount,
	                                                         @RequestParam(name = "page", defaultValue = "1") int page) {

		log.info("회차 : {} ", trainingCount);

		Page<TrainingDTO> trainingList = trainingService.selectTrainingListByTrainingCount(trainingCount, page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(trainingList);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(trainingList.getContent());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
}
