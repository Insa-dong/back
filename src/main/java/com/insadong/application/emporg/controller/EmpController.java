package com.insadong.application.emporg.controller;

import com.insadong.application.common.ResponseDTO;
import com.insadong.application.emporg.dto.EmpDTO;
import com.insadong.application.emporg.service.EmpService;
import com.insadong.application.paging.Pagenation;
import com.insadong.application.paging.PagingButtonInfo;
import com.insadong.application.paging.ResponseDTOWithPaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDTO> selectEmpList(@RequestParam(name = "page", defaultValue = "1") int page){
        log.info("[EmpController] : selectEmpList start ==================================== ");
        log.info("[EmpController] : page : {}", page);

        Page<EmpDTO> empDTOList = empService.selectEmpList(page);

        PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(empDTOList);

        log.info("[EmpController] : pageInfo : {}", pageInfo);

        ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
        responseDTOWithPaging.setPageInfo(pageInfo);
        responseDTOWithPaging.setData(empDTOList.getContent());

        log.info("[EmpController] : selectEmpList end ==================================== ");

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
    }

    /*2. 구성원 부서별 조회*/
    @GetMapping("/emp/dept/{deptCode}")
    public ResponseEntity<ResponseDTO> selectEmpListByDept(
            @RequestParam(name = "page", defaultValue = "1") int page, @PathVariable String deptCode){

        log.info("[EmpController] : selectEmpListByDept start ==================================== ");
        log.info("[EmpController] : page : {}", page);

        Page<EmpDTO> empDTOList = empService.selectEmpListByDept(page, deptCode);
        PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(empDTOList);
        log.info("[EmpController] : pageInfo : {}", pageInfo);

        ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
        responseDTOWithPaging.setPageInfo(pageInfo);
        responseDTOWithPaging.setData(empDTOList.getContent());

        log.info("[EmpController] : selectEmpListByDept end ==================================== ");

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

        Page<EmpDTO> empDTOList = empService.searchEmpByNameAndDeptAndJob(page, searchOption, searchKeyword);

        PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(empDTOList);

        log.info("[EmpController] : pageInfo : {}", pageInfo);

        ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
        responseDTOWithPaging.setPageInfo(pageInfo);
        responseDTOWithPaging.setData(empDTOList.getContent());

        log.info("[EmpController] : searchEmpByNameAndDeptAndJob end ==================================== ");

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
    }

}
