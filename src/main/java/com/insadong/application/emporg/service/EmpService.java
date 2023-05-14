package com.insadong.application.emporg.service;

import com.insadong.application.common.entity.Student;
import com.insadong.application.emporg.dto.EmpDTO;
import com.insadong.application.emporg.entity.Emp;
import com.insadong.application.emporg.repository.EmpRepository;
import com.insadong.application.student.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmpService {

    private final EmpRepository empRepository;
    private final ModelMapper modelMapper;

    public EmpService(EmpRepository empRepository, ModelMapper modelMapper) {
        this.empRepository = empRepository;
        this.modelMapper = modelMapper;
    }

    /*1. 구성원 전체 조회*/
    public Page<EmpDTO> selectEmpList(int page) {

        log.info("[EmpService] selectEmpList start ============================== ");

        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Emp> empList = empRepository.findAll(pageable);
        Page<EmpDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmpDTO.class));

        log.info("[EmpService] selectEmpList.getContent() : {}", empDTOList.getContent());

        log.info("[EmpService] selectEmpList end ============================== ");

        return empDTOList;
    }
}
