package com.insadong.application.off.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.off.dto.OffDTO;
import com.insadong.application.off.service.OffService;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;


@RestController
@RequestMapping("/off")
public class OffController {
	
	
	private final OffService offService;
	
	public OffController(OffService offService) {
		this.offService = offService;
	}
		
	/*1. 연차 신청*/
    @PostMapping("/apply")
    public ResponseEntity<String> applyOff(@RequestBody OffDTO offDTO) {
        offService.applyOff(offDTO);
        return ResponseEntity.ok("신청 완료");
    }

		
	/*3. 예정 연차 조회*/	
    @GetMapping("/my-comingUp-off")
    public ResponseEntity<ResponseDTO> myComingUpOffList(@AuthenticationPrincipal EmployeeDTO emp,
	                                                        @RequestParam(name = "page", defaultValue = "1") int page) {
    	
    	Page<OffDTO> offPage  = offService.myComingUpOffList(emp.getEmpCode(), page);
    	
    	// offEnd가 오늘 이후인 항목만 필터링
        LocalDate today = LocalDate.now();
        
        List<OffDTO> offDTOList = offPage.getContent().stream()
                .filter(off -> off.getOffEnd().isAfter(today) || off.getOffEnd().isEqual(today))
                .collect(Collectors.toList());
        
	    PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(offPage);

	    ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
	    responseDTOWithPaging.setPageInfo(pageInfo);
	    responseDTOWithPaging.setData(offDTOList);
	

	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
    	
    }
    


}
