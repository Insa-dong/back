package com.insadong.application.off.controller;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insadong.application.abs.dto.AbsDTO;
import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.off.dto.OffDTO;
import com.insadong.application.off.service.OffService;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    	
    	log.info("[OffController] applyOff start ------------------- ");
    
        offService.applyOff(offDTO, loggedInUser);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "등록 성공"));
    }


	/*3. 예정 연차 조회*/	
    @GetMapping("/my-comingUp-off")
    public ResponseEntity<ResponseDTO> myComingUpOffList(@AuthenticationPrincipal EmpDTOImplUS emp) {
    	
    	List<OffDTO> offDTOList = offService.myOffList(emp.getEmpCode());
    	
    	// offEnd가 오늘 이후인 항목만 필터링
        LocalDate today = LocalDate.now();
        
        offDTOList = offDTOList.stream()
                .filter(off -> off.getOffEnd().isAfter(today) || off.getOffEnd().isEqual(today))
                .collect(Collectors.toList());
       
        

	    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", offDTOList));
    	
    }
    
    
	/*4. 연차 사용 기록 조회 : 전체 조회, 연도 조회 한번에 작성 */	
    @GetMapping("/my-past-off")
    public ResponseEntity<ResponseDTO> myPastOffList(@AuthenticationPrincipal EmpDTOImplUS emp, @RequestParam Optional<Integer> year) {

        List<OffDTO> offDTOList = offService.myOffList(emp.getEmpCode());

        // offEnd가 오늘 이전인 항목만 필터링
        LocalDate today = LocalDate.now();

        offDTOList = offDTOList.stream()
                .filter(off -> off.getOffEnd().isBefore(today) && "승인".equals(off.getSignStatus()))
                .collect(Collectors.toList());

        // 연도가 주어졌을 때, 그 연도의 데이터만 필터링
        if (year.isPresent()) {
            offDTOList = offDTOList.stream()
                    .filter(off -> off.getOffEnd().getYear() == year.get())
                    .collect(Collectors.toList());
        }
        
        //최근 날짜부터
        offDTOList = offDTOList.stream()
        	    .sorted(Comparator.comparing(OffDTO::getOffStart).reversed())
        	    .collect(Collectors.toList());

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", offDTOList));
    }
    
    /* 5. 연차 신청 취소 - 삭제 */
    @DeleteMapping("/cancelOff/{signCode}")
    public ResponseEntity<ResponseDTO> cancelOff(@PathVariable Long signCode, @AuthenticationPrincipal EmpDTOImplUS loggedInUser) {
    	
    	offService.deleteOff(signCode, loggedInUser);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "삭제 성공"));
    }
    
    
    /* 6. 연차 신청 내역 조회 + 검색 (팀장) */
    @GetMapping("/mySignOff")
    public ResponseEntity<ResponseDTO> mySignOffList (
            @AuthenticationPrincipal EmpDTOImplUS loggedInUser,
            @RequestParam(name="page", defaultValue="1") int page,
            @RequestParam(name="searchOption", required = false) String searchOption, 
            @RequestParam(name="searchKeyword", required = false) String searchKeyword) {
        
    	log.info("[OffController] : mySignOffList start ==================================== ");
		log.info("[OffController] : page : {}", page);
		log.info("[OffController] : searchOption : {}", searchOption);
		log.info("[OffController] : searchKeyword : {}", searchKeyword);
        
		Page<OffDTO> offDTOList;

        // 검색 옵션과 키워드가 있을 경우에만 검색 수행
        if(searchOption != null && searchKeyword != null) {
            offDTOList = offService.searchOffByRequesterAndStatus(page, searchOption, searchKeyword);
        } else {
            offDTOList = offService.mySignOffList(loggedInUser.getEmpCode(), page);
        }
        
        PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(offDTOList);
        
        ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
        responseDTOWithPaging.setPageInfo(pageInfo);
        responseDTOWithPaging.setData(offDTOList.getContent()); 
        
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
    } 
    
    
    
    /* 7. 연차 승인 처리 (팀장) */
    @PutMapping("/mySignOff/{signCode}")
    public ResponseEntity<ResponseDTO> signUpOff(@PathVariable Long signCode, @RequestBody OffDTO offDTO) {
    	
    	offService.signUpOff(signCode, offDTO);
    	
    	log.info("[OffController] signUpOff start ------------------- ");
    	
    	
    	return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "승인 처리 성공"));
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}