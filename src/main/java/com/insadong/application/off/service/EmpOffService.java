package com.insadong.application.off.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Off;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.off.dto.EmpOffDTO;
import com.insadong.application.off.dto.OffDTO;
import com.insadong.application.off.repository.EmpOffRepository;

@Service
public class EmpOffService {
	
//	private final EmpOffRepository empOffRepository;
//	private final ModelMapper modelMapper;
//	
//	public EmpOffService(EmpOffRepository empOffRepository, ModelMapper modelMapper) {
//        this.empOffRepository = empOffRepository;
//        this.modelMapper = modelMapper;
//    }
//
//	public Page<EmpOffDTO> getEmployeeOffStatus(int page) {
//		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("deptCode"));
//		Page<Employee> employees = empOffRepository.findAllActiveEmployeesWithOff(pageable);
//        return employees.map(this::convertToDTO);
//    }
//
//    private EmpOffDTO convertToDTO(Employee employee) {
//    	EmpOffDTO dto = new EmpOffDTO();
//
//        dto.setEmpName(employee.getEmpName());
//        dto.setDeptName(employee.getDept().getDeptName());
//        dto.setJobName(employee.getJob().getJobName());
//
//        long usedOff = employee.getOffs().stream()
//            .filter(off -> "승인".equals(off.getSignStatus()))
//            .mapToLong(Off::getOffDay)
//            .sum();
//        dto.setUsedOff(usedOff);
//        dto.setOffCount(15L - usedOff);  // remaining off
//
//        List<OffDTO> offDTOs = employee.getOffs().stream()
//                .map(this::convertToOffDTO)
//                .collect(Collectors.toList());
//        dto.setOffs(offDTOs);
//            
//        return dto;
//    }
//
//    private OffDTO convertToOffDTO(Off off) {
//        OffDTO offDto = modelMapper.map(off, OffDTO.class);
//        offDto.setSignRequester(modelMapper.map(off.getSignRequester(), EmployeeDTO.class));
//        offDto.setSignPayer(modelMapper.map(off.getSignPayer(), EmployeeDTO.class));
//        return offDto;
//    }
}