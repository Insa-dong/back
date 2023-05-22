package com.insadong.application.off.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.off.dto.EmpOffDTO;
import com.insadong.application.off.dto.OffDTO;

@Service
public class EmpOffService {
	
	private final EmployeeRepository employeeRepository;
	public static final Long TOTAL_OFF_LEAVE = 15L; // 총 연차 15개로 고정
	
    public EmpOffService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /* 2. 내 연차 현황 조회 */
    public EmpOffDTO getEmpOffInfo(Long empCode) {
        // 직원 정보 조회
        Employee employee = employeeRepository.findById(empCode)
                .orElseThrow(() -> new RuntimeException("직원 조회 실패"));

        
        EmpOffDTO empOffDTO = new EmpOffDTO();
        // 필요한 필드 값 설정
        empOffDTO.setOffCount(employee.getOffCount());
        empOffDTO.setRemainingOff(calculateRemainingOff(employee, empOffDTO));
        empOffDTO.setTotalOff(TOTAL_OFF_LEAVE);
        empOffDTO.setUsedOff(calculateUsedOff(empOffDTO));

        return empOffDTO;
    }
    
    //사용 연차 가져오기
    private Double calculateUsedOff(EmpOffDTO empOffDTO) {
        
        List<OffDTO> offs = empOffDTO.getOffs();
        Double usedOff = offs.stream()
                             .filter(off -> off.getSignStatus().equals("승인"))
                             .mapToDouble(OffDTO::getOffDay)
                             .sum();
        return usedOff;
    }
    
    // 남은 연차 계산
    private Double calculateRemainingOff(Employee employee, EmpOffDTO empOffDTO) {
        
        Double offCount = employee.getOffCount();
        Double usedOff = calculateUsedOff(empOffDTO);
        Double remainingOff = Math.max(offCount - usedOff, 0L);
        return remainingOff;
    }


	

}