package com.insadong.application.emporg.controller;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.emporg.dto.EmpHRDTO;
import com.insadong.application.emporg.dto.RestDTO;
import com.insadong.application.emporg.service.EmpService;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/insa/v1")
public class EmpController {

	private final EmpService empService;

	public EmpController(EmpService empService) {
		this.empService = empService;
	}

	/*1. 구성원 전체 조회*/
	@GetMapping("/emp")
	public ResponseEntity<ResponseDTO> selectEmpList(@RequestParam(name = "page", defaultValue = "1") int page) {
		log.info("[EmpController] : selectEmpList start ====================================");
		log.info("[EmpController] : page : {}", page);

		List<String> empStates = Arrays.asList("재직중", "휴직중");
		Page<EmployeeDTO> empDTOList = empService.selectEmpList(page, empStates);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(empDTOList);

		log.info("[EmpController] : pageInfo : {}", pageInfo);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(empDTOList.getContent());

		log.info("[EmpController] : selectEmpList end ====================================");

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}


	/*2. 구성원 부서별 조회*/
	@GetMapping("/emp/dept/{deptCode}")
	public ResponseEntity<ResponseDTO> selectEmpListByDept(
			@RequestParam(name = "page", defaultValue = "1") int page, @PathVariable String deptCode) {

		log.info("[EmpController] : selectEmpListByDept start ==================================== ");
		log.info("[EmpController] : page : {}", page);

		Page<EmployeeDTO> empDTOList = empService.selectEmpListByDept(page, deptCode);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(empDTOList);
		log.info("[EmpController] : pageInfo : {}", pageInfo);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(empDTOList.getContent());

		log.info("[EmpController] : selectEmpListByDept end ==================================== ");

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

	/* 13. 휴직 검색*/
	@GetMapping("/emp/emprestsearch")
	public ResponseEntity<ResponseDTO> searchRestList (
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "searchOption") String searchOption,
			@RequestParam(name = "searchKeyword") String searchKeyword){

		log.info("[EmpController] : searchRestList start ==================================== ");
		log.info("[EmpController] : page : {}", page);
		log.info("[EmpController] : searchOption : {}", searchOption);
		log.info("[EmpController] : searchKeyword : {}", searchKeyword);

		Page<RestDTO> restDTOList = empService.searchRestList(page, searchOption, searchKeyword);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(restDTOList);

		log.info("[EmpController] : pageInfo : {}", pageInfo);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(restDTOList.getContent());

		log.info("[EmpController] : searchEmpByNameAndDeptAndJob end ==================================== ");
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

	/*3. 구성원 검색*/
	@GetMapping("/empsearch")
	public ResponseEntity<ResponseDTO> searchEmpByNameAndDeptAndJob(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "searchOption") String searchOption,
			@RequestParam(name = "searchKeyword") String searchKeyword) {

		log.info("[EmpController] : searchEmpByNameAndDeptAndJob start ==================================== ");
		log.info("[EmpController] : page : {}", page);
		log.info("[EmpController] : searchOption : {}", searchOption);
		log.info("[EmpController] : searchKeyword : {}", searchKeyword);

		Page<EmployeeDTO> empDTOList = empService.searchEmpByNameAndDeptAndJob(page, searchOption, searchKeyword);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(empDTOList);

		log.info("[EmpController] : pageInfo : {}", pageInfo);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(empDTOList.getContent());

		log.info("[EmpController] : searchEmpByNameAndDeptAndJob end ==================================== ");

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

	/*4. 구성원 부서 직책 조회*/
	@GetMapping("/emp/deptjoblist")
	public ResponseEntity<ResponseDTO> selectEmpDeptJobList() {
		log.info("[EmpController] : selectEmpDeptJobList start ==================================== ");
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", empService.selectEmpDeptJobList()));
	}


	/*5. 구성원 등록*/
	@PostMapping("/emp/empregist")
	public ResponseEntity<ResponseDTO> insertEmp(@RequestBody EmployeeDTO employeeDTO) {
		log.info("[EmpController] employeeDTO : {}", employeeDTO);
		empService.insertEmp(employeeDTO);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "구성원 등록 성공"));
	}


	/* 6. 구성원 상세 조회 */
	@GetMapping("emp/empdetail/{empCode}")
	public ResponseEntity<ResponseDTO> selectEmpDetail(@PathVariable Long empCode) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", empService.selectEmpDetail(empCode)));
	}

	/* 7. 인사이력 조회*/
	@GetMapping("/emp/emprecord/{empCode}")
	public ResponseEntity<ResponseDTO> selectEmpRecord(@RequestParam(name = "page", defaultValue = "1") int page,
													   @PathVariable Long empCode) {
		log.info("[EmpController] : selectEmpRecord start ==================================== ");
		log.info("[EmpController] : page : {}", page);

		Page<EmpHRDTO> empHRDTOList = empService.selectEmpRecord(page, empCode);

		log.info("empHRDTOList {}", empHRDTOList);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(empHRDTOList);

		log.info("[EmpController] : pageInfo : {}", pageInfo);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(empHRDTOList.getContent());

		log.info("[EmpController] : selectEmpRecord end ==================================== ");

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}

	/* 구성원 부서이동*/
	@PutMapping("/emp/empupdatedept")
	public ResponseEntity<ResponseDTO>	updateEmpDept(@RequestBody EmployeeDTO employeeDTO){
		empService.updateEmpDept(employeeDTO);

		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "구성원 부서이동 성공"));
	}



	/* 구성원 직책변경*/
	@PutMapping("/emp/empupdatejob")
	public ResponseEntity<ResponseDTO>	updateEmpJob(@RequestBody EmployeeDTO employeeDTO){
		empService.updateEmpJob(employeeDTO);

		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "구성원 직책변경 성공"));
	}
	/*휴직 신청*/
	@PostMapping("/emp/emprestregist")
	public ResponseEntity<ResponseDTO> insertEmpRest(@RequestBody RestDTO restDTO){
		empService.insertEmpRest(restDTO);

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "구성원 휴직 신청 성공"));
	}

	/* 휴직 리스트 */
	@GetMapping("emp/emprestlist")
	public ResponseEntity<ResponseDTO> selectRestList(@RequestParam(name = "page", defaultValue = "1") int page) {

		log.info("[EmpController] : page : {}", page);
		Page<RestDTO> restDTOList = empService.selectRestList(page);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(restDTOList);

		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageInfo(pageInfo);
		responseDTOWithPaging.setData(restDTOList.getContent());

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "휴직 리스트 조회 성공", responseDTOWithPaging));
	}

	/*구성원 삭제*/
	@PutMapping("/emp/empdelete/{empCode}")
	public ResponseEntity<ResponseDTO> deleteEmp(@PathVariable Long empCode) {
		empService.deleteEmp(empCode);
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "구성원 삭제 성공"));
	}

	/* 12. 휴직 상태 변경*/
	@PutMapping("emp/empreststate")
	public ResponseEntity<ResponseDTO> updateRestState(@RequestBody RestDTO restDTO){
		empService.updateRestState(restDTO);

		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "휴직처리 성공"));
	}

	@GetMapping("/emp/teacher")
	public ResponseEntity<ResponseDTO> viewTeacherList() {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", empService.viewTeacherList()));
	}


}
