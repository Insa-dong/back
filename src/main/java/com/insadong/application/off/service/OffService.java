package com.insadong.application.off.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Off;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.off.dto.OffDTO;
import com.insadong.application.off.repository.EmpOffRepository;
import com.insadong.application.off.repository.OffRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OffService {

	public static final Long TOTAL_OFF_LEAVE = 15L;
	private final OffRepository offRepository;
	private final EmpOffRepository empOffRepository;
	private final ModelMapper modelMapper;

	public OffService(OffRepository offRepository, EmpOffRepository empOffRepository, ModelMapper modelMapper) {
		this.offRepository = offRepository;
		this.empOffRepository = empOffRepository;
		this.modelMapper = modelMapper;
	}

	/* 연차 신청 */
	@Transactional
	public void applyOff(OffDTO offDTO, EmpDTOImplUS loggedInUser) {

		Off off = new Off();

		LocalDate offStart = offDTO.getOffStart();
		LocalDate offEnd = offDTO.getOffEnd();

		off.setOffStart(offStart);
		off.setOffEnd(offEnd);
		off.setOffDiv(offDTO.getOffDiv());
		off.setSignReason(offDTO.getSignReason());
		off.setSignStatus("대기"); // 고정값

		// 연차 일수 계산
		long days = ChronoUnit.DAYS.between(offStart, offEnd) + 1;

		// 반차면 0.5개로
		double offDay = offDTO.getOffDiv().contains("반차") ? 0.5 : days;
		off.setOffDay(offDay);
	
		// 신청자 설정
		Employee foundEmp = empOffRepository.findById(loggedInUser.getEmpCode()).orElseThrow(() -> new IllegalArgumentException("asd"));
//	    Employee requester = modelMapper.map(loggedInUser, Employee.class);
	    off.setSignRequester(foundEmp);
	
		// 결재자 = 신청자 부서 팀장
		Employee payer = empOffRepository.findTeamLeaderByDept(foundEmp.getDept());
		off.setSignPayer(payer);

		// 신청일 설정
		LocalDate requestDate = LocalDate.now();
		off.setRequestDate(Date.from(requestDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		offRepository.save(off);

	}
	
	/*2. 연차 중복 조회 */
	
	public boolean checkExistingOff(LocalDate offStart, LocalDate offEnd) {
	    List<String> signStatusList = Arrays.asList("승인", "대기");
	    return offRepository.existsByOffStartLessThanEqualAndOffEndGreaterThanEqualAndSignStatusIn(offStart, offEnd, signStatusList);
	}
	
	/* 3,4. 내 연차 조회 */
	public List<OffDTO> myOffList(Long empCode) {
		List<Off> offList = offRepository.findBySignRequester_EmpCode(empCode, Sort.by("offStart"));
		log.info("offList : {} ", offList);

		List<OffDTO> offDTOList = offList.stream().map(off -> modelMapper.map(off, OffDTO.class))
				.collect(Collectors.toList());

		return offDTOList;

	}

	
}
