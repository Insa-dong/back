package com.insadong.application.abs.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.abs.dto.AbsDTO;
import com.insadong.application.abs.service.AbsService;
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.common.entity.Employee;
import com.insadong.application.employee.dto.EmployeeDTO;
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
	
	/* 1. 모든 근태 조회 (관리자)*/
	@GetMapping("/abs-admin")
	public ResponseEntity<ResponseDTO> selectAbsListForAdmin(@RequestParam(name="page", defaultValue="1") int page) {
		

		Page<AbsDTO> absDtoList = absService.selectAbsServiceListForAdmin(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(absDtoList);
		
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
	    responseDTOWithPaging.setPageInfo(pageInfo);
	    responseDTOWithPaging.setData(absDtoList.getContent()); 
	    

		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	/*1-1 자신의 근태 조회 */
	@GetMapping("/abs-myAbs")
	public ResponseEntity<ResponseDTO> selectAbsListForUser(@AuthenticationPrincipal Employee empCode,
	                                                        @RequestParam(name = "page", defaultValue = "1") int page) {
		
	    if (empCode == null) {
	        // empCode가 null인 경우에 대한 처리
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(HttpStatus.BAD_REQUEST, "사용자 정보가 유효하지 않습니다.", null));
	    }

	    Page<AbsDTO> absDtoList = absService.selectAbsListForUser(empCode, page);
	    

	    PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(absDtoList);

	    ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
	    responseDTOWithPaging.setPageInfo(pageInfo);
	    responseDTOWithPaging.setData(absDtoList.getContent());

	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	
	/* 2. 출근 시간 등록*/
	@PostMapping("/checkIn")
	public ResponseEntity<ResponseDTO> checkIn (@AuthenticationPrincipal EmployeeDTO loggedInUser) {
		Long empCode = loggedInUser.getEmpCode();
	    try {
	        absService.checkIn(empCode);
	        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "출근 등록 성공"));
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO(HttpStatus.CONFLICT, e.getMessage()));
	    }
	}
	
	/* 3. 퇴근 시간 등록*/
	@PutMapping("/checkOut")
	public ResponseEntity<ResponseDTO> checkOut (@AuthenticationPrincipal EmployeeDTO loggedInUser) {
		Long empCode = loggedInUser.getEmpCode();
	    try {
	        absService.checkOut(empCode);
	        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "퇴근 등록 성공"));
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO(HttpStatus.CONFLICT, e.getMessage()));
	    }
	}
	
	
	/* 4. 근태 날짜 조회 */

	@GetMapping("/abs-admin/{absDate}")
	public ResponseEntity<ResponseDTO> selectAbsDateForAdmin(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate absDate,
	        @RequestParam(name="page", defaultValue="1") int page) {

	    Page<AbsDTO> absDtoList = absService.selectAbsDateForAdmin(absDate, page);
	    
	    PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(absDtoList);
	    
	    ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
	    responseDTOWithPaging.setPageInfo(pageInfo);
	    responseDTOWithPaging.setData(absDtoList.getContent()); 
	    
	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	/* 5. 관리자 근태 수정 */
	@PutMapping("/abs-admin")
	public ResponseEntity<ResponseDTO> updateAbs(@ModelAttribute AbsDTO absDTO) {
		
		absService.modifyAbs(absDTO);
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "근태 수정 성공"));
		
	}
	
	

}
