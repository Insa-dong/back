package com.insadong.application.off.controller;

import com.insadong.application.off.service.EmpOffService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/off")
public class EmpOffController {

	private final EmpOffService empOffService;

	public EmpOffController(EmpOffService empOffService) {
		this.empOffService = empOffService;

	}

//	@GetMapping("/offNowjjj")
//    public ResponseEntity<ResponseDTO> getEmployeesOffStatus(@RequestParam(name="page", defaultValue="1") int page) {
//
//		Page<EmpOffDTO> empOffDTOList = empOffService.getEmployeeOffStatus(page);
//		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(empOffDTOList);
//
//
//		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
//	    responseDTOWithPaging.setPageInfo(pageInfo);
//	    responseDTOWithPaging.setData(empOffDTOList.getContent());
//
//
//
//		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
//    }

}
