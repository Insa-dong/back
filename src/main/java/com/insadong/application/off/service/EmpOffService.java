package com.insadong.application.off.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Dept;
import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Off;
import com.insadong.application.employee.dto.DeptDTO;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.off.dto.EmpOffDTO;
import com.insadong.application.off.repository.EmpOffRepository;
import com.insadong.application.off.repository.OffRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    /* 1. 내 연차 현황 */
	public EmpOffDTO showMyOff(Long empCode) {
		log.info("[EmpOffService] showMyOff start -----------------------------" );
				
		// 직원 정보 조회
		Employee emp = employeeRepository.findById(empCode).orElseThrow(() -> new RuntimeException("직원 조회 실패"));
        
		EmpOffDTO empOffDTO = new EmpOffDTO();
		
		 // 연차 정보 계산
	    List<Off> offs = findOffsByEmpCodeAndStatus(empCode, "승인");
	    
	    int currentYear = LocalDate.now().getYear();
	    
	    //올해 연차만
	    Double usedOff = offs.stream()
	            .filter(off -> off.getOffEnd().getYear() == currentYear)
	            .mapToDouble(Off::getOffDay)
	            .sum();
	    Double remainingOff = emp.getOffCount() - usedOff;
	    log.info("[EmpOffService] remainingOff {} :  ", remainingOff );
	    

	    // DTO에 연차 정보 설정
	    empOffDTO.setOffCount(emp.getOffCount()); // 총 연차 설정
	    empOffDTO.setUsedOff(usedOff); // 사용한 연차 설정
	    empOffDTO.setRemainingOff(remainingOff); // 잔여 연차 설정
	    
	    log.info("[EmpOffService] empOffDTO{}: ", empOffDTO  );
	    
	    log.info("[EmpOffService] showMyOff end -----------------------------" );
		
        return empOffDTO;
        
        
	}

	private List<Off> findOffsByEmpCodeAndStatus(Long empCode, String signStatus) {
		return offRepository.findBySignRequester_EmpCodeAndSignStatus(empCode, signStatus);
	}

	/* 2. 팀원 연차 조회(팀장) */
	public List<EmpOffDTO> getTeamOff(Long empCode) {
		Employee emp = employeeRepository.findById(empCode)
				.orElseThrow(() -> new RuntimeException("직원 조회 실패"));
		
		String deptCode = emp.getDept().getDeptCode(); //팀장 부서 코드
		List<Employee> deptMembers = empOffRepository.findByDept_DeptCode(deptCode);
		List<EmpOffDTO> teamOffList = new ArrayList<>();
		
		for(Employee deptMember : deptMembers ) {
			
			EmpOffDTO empOffDTO = new EmpOffDTO();
			
			empOffDTO.setEmpCode(deptMember.getEmpCode()); // 직원 코드 설정
	        empOffDTO.setDept(convertToDeptDTO(deptMember.getDept())); // 부서 정보 설정
		
		
	      // 연차 정보 계산
	      List<Off> offs = findOffsByEmpCodeAndStatus(deptMember.getEmpCode(), "승인");
	      int currentYear = LocalDate.now().getYear();
	      
	      // 올해 연차만
	      Double usedOff = offs.stream()
	            .filter(off -> off.getOffEnd().getYear() == currentYear)
	            .mapToDouble(Off::getOffDay)
	            .sum();
	      Double remainingOff = deptMember.getOffCount() - usedOff;
	      
	      // DTO에 연차 정보 설정
	      empOffDTO.setEmpCode(deptMember.getEmpCode()); // 직원 코드 설정
	      empOffDTO.setOffCount(deptMember.getOffCount()); // 총 연차 설정
	      empOffDTO.setUsedOff(usedOff); // 사용한 연차 설정
	      empOffDTO.setRemainingOff(remainingOff); // 잔여 연차 설정
	      
	      teamOffList.add(empOffDTO);
	      
	}
		
	      return teamOffList;
	}

	private DeptDTO convertToDeptDTO(Dept dept) {
		DeptDTO deptDTO = new DeptDTO();
		
        deptDTO.setDeptCode(dept.getDeptCode());
        deptDTO.setDeptName(dept.getDeptName());
       

        return deptDTO;
	}

   
	
	

}