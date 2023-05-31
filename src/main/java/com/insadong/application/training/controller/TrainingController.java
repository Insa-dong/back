package com.insadong.application.training.controller;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import com.insadong.application.study.dto.PetiteTrainingDTO;
import com.insadong.application.training.dto.TrainingDTO;
import com.insadong.application.training.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

		List<PetiteTrainingDTO> data = trainingService.viewTrainingTitleList();
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", data));
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
	public ResponseEntity<ResponseDTO> modifyTraining(@RequestBody TrainingDTO trainingDTO, @AuthenticationPrincipal EmpDTOImplUS employeeDTO) {

		trainingService.updateTraining(trainingDTO, employeeDTO.getEmpCode());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정 성공"));
	}

	@DeleteMapping("/training")
	public ResponseEntity<ResponseDTO> trainingDelete(@RequestBody List<Long> trainingCode) {
		log.info("code : {} ", trainingCode);

		trainingService.trainingDelete(trainingCode);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "삭제 성공"));
	}

	@PostMapping("/training")
	public ResponseEntity<ResponseDTO> insertTraining(@RequestBody com.insadong.application.study.dto.TrainingDTO trainingDTO, @AuthenticationPrincipal EmpDTOImplUS empDTO) {

		log.info("training : {} ", trainingDTO);
		log.info("emp : {} ", empDTO);
		trainingService.insertTraining(trainingDTO, empDTO.getEmpCode());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "등록이 완료되었습니다. 메인 페이지로 이동합니다."));
	}

	@GetMapping("/trainingList/search")
	public ResponseEntity<ResponseDTO> searchTrainingByTitle(@RequestParam(name = "search") String trainingTitle,
	                                                         @RequestParam(name = "page", defaultValue = "1") int page) {


		Page<TrainingDTO> trainingList = trainingService.selectTrainingListByTrainingTitle(trainingTitle, page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(trainingList);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(trainingList.getContent());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

}
