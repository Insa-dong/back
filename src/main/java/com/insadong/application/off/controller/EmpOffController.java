package com.insadong.application.off.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.off.dto.EmpOffDTO;
import com.insadong.application.off.service.EmpOffService;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;

@RestController
@RequestMapping("/off")
public class EmpOffController {
	

	private final EmpOffService empOffService;

	public EmpOffController(EmpOffService empOffService) {
		this.empOffService = empOffService;

	}

	/* 1. 내 연차 현황 조회 */

	   @GetMapping("/myOff")
	    public ResponseEntity<ResponseDTO> getMyOff(@AuthenticationPrincipal EmpDTOImplUS emp) {

		   EmpOffDTO empOffDTO = empOffService.showMyOff(emp.getEmpCode());

	        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "내 연차 현황 조회 성공", empOffDTO));
	    }
	   
	 /* 2. 팀원 연차 현황 조회 */
	   @GetMapping("/teamOff")
	   public ResponseEntity<ResponseDTO> getTeamOff(@AuthenticationPrincipal EmpDTOImplUS emp,
			   @RequestParam(name="page", defaultValue="1") int page,
			   @RequestParam(name="searchOption", required = false) String searchOption, 
	           @RequestParam(name="searchKeyword", required = false) String searchKeyword) {
		   
		   Page<EmpOffDTO> teamOffList = empOffService.getTeamOff(emp.getEmpCode(), page, searchOption, searchKeyword);
		   
		   PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(teamOffList);
	        
	        ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
	        responseDTOWithPaging.setPageInfo(pageInfo);
	        responseDTOWithPaging.setData(teamOffList.getContent()); 
	        
		   return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "팀원 연차 현황 조회 성공", responseDTOWithPaging));
		}
	   
	   /*3. 구성원 연차 현황 조회 */
	   
	   @GetMapping("/adminOff")
	   public ResponseEntity<ResponseDTO> getEmpOff(
			   @RequestParam(name="page", defaultValue="1") int page,
			   @RequestParam(name="searchOption", required = false) String searchOption, 
	           @RequestParam(name="searchKeyword", required = false) String searchKeyword) {
		   
		   Page<EmpOffDTO> empOffList = empOffService.getEmpOff(page, searchOption, searchKeyword);
		   
		   PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(empOffList);
	        
	        ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
	        responseDTOWithPaging.setPageInfo(pageInfo);
	        responseDTOWithPaging.setData(empOffList.getContent()); 
	        
		   return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "구성원 연차 현황 조회 성공", responseDTOWithPaging));
		}
	   
	   
	   
	}


