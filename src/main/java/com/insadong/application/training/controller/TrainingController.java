package com.insadong.application.training.controller;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import com.insadong.application.training.dto.TrainingDTO;
import com.insadong.application.training.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class TrainingController {

	private final TrainingService trainingService;

	public TrainingController(TrainingService trainingService) {
		this.trainingService = trainingService;
	}

	@GetMapping("/trainingList")
	public ResponseEntity<ResponseDTO> viewTrainingList(@RequestParam(name = "page", defaultValue = "1") int page) {
		/* @AuthenticationPrincipal 에서 받아와야 함. 했다 치고 */
		long empCode = 1000;

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
	public ResponseEntity<ResponseDTO> modifyTraining(@RequestBody TrainingDTO trainingDTO) {

		log.info("DTO : {} ", trainingDTO);
		long empCode = 1000;
		trainingService.updateTraining(trainingDTO, empCode);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정 성공"));
	}
}
