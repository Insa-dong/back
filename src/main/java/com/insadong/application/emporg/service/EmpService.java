package com.insadong.application.emporg.service;

import com.insadong.application.emporg.dto.EmpDTO;
import com.insadong.application.emporg.entity.Emp;
import com.insadong.application.emporg.entity.EmpDept;
import com.insadong.application.emporg.repository.EmpDeptRepository;
import com.insadong.application.emporg.repository.EmpRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class EmpService {

    private final EmpRepository empRepository;
    private final ModelMapper modelMapper;
    private final EmpDeptRepository empDeptRepository;

    public EmpService(EmpRepository empRepository, ModelMapper modelMapper, EmpDeptRepository empDeptRepository) {
        this.empRepository = empRepository;
        this.modelMapper = modelMapper;
        this.empDeptRepository = empDeptRepository;
    }

    /*1. 구성원 전체 조회*/
    public Page<EmpDTO> selectEmpList(int page) {

        log.info("[EmpService] selectEmpList start ============================== ");

        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("empCode").descending());

        Page<Emp> empList = empRepository.findAll(pageable);
        Page<EmpDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmpDTO.class));

        log.info("[EmpService] selectEmpList.getContent() : {}", empDTOList.getContent());

        log.info("[EmpService] selectEmpList end ============================== ");

        return empDTOList;
    }

    /*2. 구성원 부서별 조회*/
    public Page<EmpDTO> selectEmpListByDept(int page, String deptCode){

        log.info("[EmpService] selectEmpListByDept start ============================== ");

        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("empCode").descending());

        /*dept 엔티티 조회*/
        EmpDept findDept = empDeptRepository.findById(deptCode)
                .orElseThrow(()-> new IllegalArgumentException("해당 부서가 없습니다. deptCode ="+ deptCode));


        Page<Emp> empList = empRepository.findByDept(pageable, findDept);
        Page<EmpDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmpDTO.class));

        log.info("[EmpService] selectEmpListByDept.getContent() : {}", empDTOList.getContent());

        log.info("[EmpService] selectEmpListByDept end ============================== ");

        return empDTOList;
    }
}
