package com.insadong.application.off.service;

import java.time.LocalDate;
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
        Employee employee = employeeRepository.findById(empCode).orElseThrow(() -> new RuntimeException("직원 조회 실패"));

        EmpOffDTO empOffDTO = new EmpOffDTO();
        // 필요한 필드 값 설정
        empOffDTO.setEmpCode(employee.getEmpCode());
        empOffDTO.setEmpName(employee.getEmpName());
        empOffDTO.setEmpId(employee.getEmpId());
        empOffDTO.setEmpPwd(employee.getEmpPwd());
        empOffDTO.setEmpState(employee.getEmpState());
        empOffDTO.setEmpAuthList(employee.getEmpAuthList());
        //empOffDTO.setOffs(employee.getOffs());;

        return empOffDTO;
    }

    // 사용한 연차 계산
    public Double calculateUsedOff(EmpOffDTO empOffDTO) {
        int currentYear = LocalDate.now().getYear();

        List<OffDTO> offs = empOffDTO.getOffs();

        Double usedOff = offs.stream()
                .filter(off -> off.getOffEnd().getYear() == currentYear)
                .filter(off -> off.getSignStatus().equals("승인"))
                .mapToDouble(OffDTO::getOffDay)
                .sum();

        return usedOff;
    }

    // 남은 연차 계산
    public Double calculateRemainingOff(Double offCount, Double usedOff) {
        return Math.max(offCount - usedOff, 0);
    }
    
    // 총 연차 변경
    public void updateTotalOffLeave(int totalOffLeave) {
        // 총 연차를 변경하고 싶은 경우, 이 메소드를 호출하여 값을 업데이트합니다.
        // 총 연차를 저장하는 방식은 환경 변수, 설정 파일, 데이터베이스 등을 사용할 수 있습니다.
        // 여기서는 단순히 상수로 처리하였습니다.
    }

    // offCount 업데이트
    public void updateOffCount(Long empCode, Double remainingOff) {
        Employee employee = employeeRepository.findById(empCode).orElseThrow(() -> new RuntimeException("직원 조회 실패"));
        employee.setOffCount(remainingOff);
        employeeRepository.save(employee);
    }
}