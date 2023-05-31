package com.insadong.application.off.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Off;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.off.dto.EmpOffDTO;
import com.insadong.application.off.repository.OffRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpOffService {

	private final EmployeeRepository employeeRepository;
	private final OffRepository offRepository;

	public EmpOffService(EmployeeRepository employeeRepository, OffRepository offRepository) {
		this.employeeRepository = employeeRepository;
		this.offRepository = offRepository;

	}

	/* 1. 내 연차 현황 */
	public EmpOffDTO showMyOff(Long empCode) {
		log.info("[EmpOffService] showMyOff start -----------------------------");

		// 직원 정보 조회
		Employee emp = employeeRepository.findById(empCode).orElseThrow(() -> new RuntimeException("직원 조회 실패"));

		EmpOffDTO empOffDTO = calculateOff(emp);

		//log.info("[EmpOffService] empOffDTO{}: ", empOffDTO);

		log.info("[EmpOffService] showMyOff end -----------------------------");

		return empOffDTO;

	}

	/* 2. 팀원 연차 조회(팀장) */
	public Page<EmpOffDTO> getTeamOff(Long empCode, int page, String searchOption, String searchKeyword) {

		log.info("[EmpOffService] getTeamOff start -----------------------------");

		Pageable pageable = PageRequest.of(page - 1, 10,
				Sort.by(Sort.Order.asc("job.jobCode"), Sort.Order.asc("empName"))); // 직급별, 이름순으로 정렬

		Employee emp = employeeRepository.findById(empCode).orElseThrow(() -> new RuntimeException("직원 조회 실패"));

		String deptCode = emp.getDept().getDeptCode(); // 팀장 부서 코드
		List<Employee> deptMembers = employeeRepository.findByDept_DeptCode(deptCode); // 팀원 구하기

		List<EmpOffDTO> teamOffList = new ArrayList<>();

		for (Employee deptMember : deptMembers) {

			// 연차 정보 계산 및 EmpOffDTO에 설정
			EmpOffDTO empOffDTO = calculateOff(deptMember);

			teamOffList.add(empOffDTO);
		}

		// 검색 조건 추가
		if (searchOption != null && !searchOption.isEmpty() && searchKeyword != null && !searchKeyword.isEmpty()) {

			if (searchOption.equals("empName")) {
				teamOffList = teamOffList.stream().filter(empOffDTO -> empOffDTO.getEmpName().contains(searchKeyword))
						.collect(Collectors.toList());
			} else if (searchOption.equals("remainingOff")) { // 검색 일수 이상 연차가 남은 팀원 조회
				double remainingOffThreshold = Double.parseDouble(searchKeyword);

				teamOffList = teamOffList.stream()
						.filter(empOffDTO -> empOffDTO.getRemainingOff() >= remainingOffThreshold)
						.collect(Collectors.toList());
			} else {
				throw new IllegalArgumentException("유효하지 않은 검색 옵션입니다.");
			}
		}

		log.info("[EmpOffService] getTeamOff end -----------------------------");

		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), teamOffList.size());

		Comparator<EmpOffDTO> comparator = Comparator.comparing(EmpOffDTO::getJobCode)
				.thenComparing(EmpOffDTO::getEmpName);

		teamOffList = teamOffList.stream().sorted(comparator).collect(Collectors.toList());

		// 검색 및 페이징된 팀원 연차 정보를 반환 -> teamOffList 는 content에 저장됨
		return new PageImpl<>(teamOffList.subList(start, end), pageable, teamOffList.size());

	}

	/* 3. 구성원 연차 현황 조회 */
	public Page<EmpOffDTO> getEmpOff(int page, String searchOption, String searchKeyword) {
		log.info("[EmpOffService] getEmpOff start -----------------------------");

		Pageable pageable = PageRequest.of(page - 1, 10,
				Sort.by(Sort.Order.asc("dept.deptCode"), Sort.Order.asc("job.jobCode"), Sort.Order.asc("empName")));

		List<Employee> empAll = employeeRepository.findAllByDept_DeptCodeNot("DE0003");
		//log.info("[EmpOffService] empAll {}: ", empAll);

		List<EmpOffDTO> empOffList = new ArrayList<>();

		for (Employee emp : empAll) {

			// 연차 정보 계산 및 EmpOffDTO에 설정
			EmpOffDTO empOffDTO = calculateOff(emp);

			empOffList.add(empOffDTO);
		}
		
		

		// 검색 조건 추가
		if (searchOption != null && !searchOption.isEmpty() && searchKeyword != null && !searchKeyword.isEmpty()) {

			if (searchOption.equals("deptName")) {
				empOffList = empOffList.stream().filter(empOffDTO -> empOffDTO.getDeptName().contains(searchKeyword))
						.collect(Collectors.toList());
			} else if (searchOption.equals("empName")) {
				empOffList = empOffList.stream().filter(empOffDTO -> empOffDTO.getEmpName().contains(searchKeyword))
						.collect(Collectors.toList());
			} else if (searchOption.equals("remainingOff")) { // 검색 일수 이상 연차가 남은 팀원 조회
				double remainingOffThreshold = Double.parseDouble(searchKeyword);

				empOffList = empOffList.stream()
						.filter(empOffDTO -> empOffDTO.getRemainingOff() >= remainingOffThreshold)
						.collect(Collectors.toList());
			} else {
				throw new IllegalArgumentException("유효하지 않은 검색 옵션입니다.");
			}
		}

		

		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), empOffList.size());

		Comparator<EmpOffDTO> comparator = Comparator.comparing(EmpOffDTO::getDeptCode)
			    .thenComparing(EmpOffDTO::getJobCode)
			    .thenComparing(EmpOffDTO::getEmpName);

		empOffList = empOffList.stream().sorted(comparator).collect(Collectors.toList());
		
		log.info("[EmpOffService] empOffList {}: ", empOffList.size());

		log.info("[EmpOffService] getEmpOff end -----------------------------");

		// 검색 및 페이징된 팀원 연차 정보를 반환 -> teamOffList 는 content에 저장됨
		return new PageImpl<>(empOffList.subList(start, end), pageable, empOffList.size());

	}

	/* 연차 정보 계산 로직 */
	public EmpOffDTO calculateOff(Employee emp) {

		// 연차 정보 계산
		List<Off> offs = offRepository.findBySignRequester_EmpCodeAndSignStatus(emp.getEmpCode(), "승인");

		int currentYear = LocalDate.now().getYear();

		// 올해 연차만
		Double usedOff = offs.stream().filter(off -> off.getOffEnd().getYear() == currentYear)
				.mapToDouble(Off::getOffDay).sum();
		Double remainingOff = emp.getOffCount() - usedOff;

		// 계산한 연차 정보를 EmpOffDTO에 설정
		EmpOffDTO empOffDTO = new EmpOffDTO();
		empOffDTO.fromEmployee(emp);
		empOffDTO.setUsedOff(usedOff); // 사용한 연차 설정
		empOffDTO.setRemainingOff(remainingOff); // 잔여 연차 설정

		return empOffDTO;

	}

}