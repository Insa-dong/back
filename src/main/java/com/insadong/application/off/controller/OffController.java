package com.insadong.application.off.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.off.dto.OffDTO;
import com.insadong.application.off.service.OffService;


@RestController
@RequestMapping("/off")
public class OffController {
	
	
	private final OffService offService;
	
	public OffController(OffService offService) {
		this.offService = offService;
	}
		
	/*1. 연차 신청*/
    @PostMapping("/apply")
    public ResponseEntity<ResponseDTO> applyOff(@RequestBody OffDTO offDTO, @AuthenticationPrincipal EmpDTOImplUS loggedInUser) {
    	// 로그인한 구성원이 신청자
//        offDTO.setSignRequester(loggedInUser);

        offService.applyOff(offDTO, loggedInUser);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "등록 성공", offDTO));
    }

    /*2. 연차 중복 조회 */
    @GetMapping("/check-existing-off")
    public ResponseEntity<ResponseDTO> checkExistingOff(@RequestParam LocalDate offStart, @RequestParam LocalDate offEnd) {
       
    	boolean isExistingOff = offService.checkExistingOff(offStart, offEnd);
       
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", isExistingOff));
    }
		
	/*3. 예정 연차 조회*/	
    @GetMapping("/my-comingUp-off")
    public ResponseEntity<ResponseDTO> myComingUpOffList(@AuthenticationPrincipal EmpDTOImplUS emp) {
    	
    	List<OffDTO> offDTOList = offService.myComingUpOffList(emp.getEmpCode());
    	
    	// offEnd가 오늘 이후인 항목만 필터링
        LocalDate today = LocalDate.now();
        
        offDTOList = offDTOList.stream()
                .filter(off -> off.getOffEnd().isAfter(today) || off.getOffEnd().isEqual(today))
                .collect(Collectors.toList());
       
        

	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", offDTOList));
    	
    }
    


}
