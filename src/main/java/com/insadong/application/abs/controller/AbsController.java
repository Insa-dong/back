package com.insadong.application.abs.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.abs.dto.AbsDTO;
import com.insadong.application.abs.service.AbsService;
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;

@RestController
@RequestMapping("/abs")
public class AbsController {
	
private final AbsService absService; 
	public AbsController(AbsService absService) {
		this.absService = absService;
	}
	
	@GetMapping("/abs-admin")
	public ResponseEntity<ResponseDTO> selectProductListForAdmin(@RequestParam(name="page", defaultValue="1") int page) {
		

		Page<AbsDTO> absDtoList = absService.selectAbsServiceListForAdmin(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(absDtoList);
		
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
	    responseDTOWithPaging.setPageInfo(pageInfo);
	    responseDTOWithPaging.setData(absDtoList.getContent()); 
	    

		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	

}
