package com.insadong.application.off.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Off;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.off.dto.EmpOffDTO;
import com.insadong.application.off.repository.EmpOffRepository;
import com.insadong.application.off.repository.OffRepository;

@Service
public class EmpOffService {

    private final EmployeeRepository employeeRepository;
    private final OffRepository offRepository;
    private final EmpOffRepository empOffRepository;

    public EmpOffService(EmployeeRepository employeeRepository, 
    		OffRepository offRepository, EmpOffRepository empOffRepository) {
        this.employeeRepository = employeeRepository;
        this.offRepository = offRepository;
        this.empOffRepository = empOffRepository;
    }

    /* 2. 내 연차 현황 */
	public EmpOffDTO showMyOff(Long empCode) {
		
		// 직원 정보 조회
		Employee emp = employeeRepository.findById(empCode).orElseThrow(() -> new RuntimeException("직원 조회 실패"));
        
		EmpOffDTO empOffDTO = new EmpOffDTO();
		
		 // 연차 정보 계산
	    List<Off> offs = findOffsByEmpCodeAndStatus(empCode, "승인");
	    
	    int currentYear = LocalDate.now().getYear();
	    
	    Double usedOff = offs.stream()
	            .filter(off -> off.getOffEnd().getYear() == currentYear)
	            .mapToDouble(Off::getOffDay)
	            .sum();
	    Double remainingOff = emp.getOffCount() - usedOff;

	    // DTO에 연차 정보 설정
	    empOffDTO.setOffCount(emp.getOffCount()); // 총 연차 설정
	    empOffDTO.setUsedOff(usedOff); // 사용한 연차 설정
	    empOffDTO.setRemainingOff(remainingOff); // 잔여 연차 설정
		
        return empOffDTO;
	}

	private List<Off> findOffsByEmpCodeAndStatus(Long empCode, String signStatus) {
		return offRepository.findBySignRequester_EmpCodeAndSignStatus(empCode, signStatus);
	}

   

}