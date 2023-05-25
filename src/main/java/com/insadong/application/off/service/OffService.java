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

		
		LocalDate offStart = offDTO.getOffStart();
		LocalDate offEnd = offDTO.getOffEnd();
		
		// 중복 여부 확인
	    if (checkExistingOff(offStart, offEnd)) {
	        throw new IllegalArgumentException("이미 신청된 연차가 존재합니다.");
	    }

		// 연차 일수 계산
		long days = ChronoUnit.DAYS.between(offStart, offEnd) + 1;

		// 반차면 0.5개로
		double offDay = offDTO.getOffDiv().contains("반차") ? 0.5 : days;

		// 신청자 설정
		Employee foundEmp = empOffRepository.findById(loggedInUser.getEmpCode()).orElseThrow(() -> new IllegalArgumentException("asd"));
		// 신청자 타입 변환 (Employee -> EmployeeDTO)
		EmployeeDTO empDTO = modelMapper.map(foundEmp, EmployeeDTO.class);
	
		// 결재자 = 신청자 부서 팀장
		Employee payer = empOffRepository.findTeamLeaderByDept(foundEmp.getDept());
		
		// 결재자 타입 변환 (Employee -> EmployeeDTO)
		EmployeeDTO payerDTO = modelMapper.map(payer, EmployeeDTO.class);

		// 신청일 설정
		LocalDate requestDate = LocalDate.now();
		Date requestDateConverted = Date.from(requestDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		OffDTO offDTOs = new OffDTO();
		offDTOs.setOffStart(offStart);
		offDTOs.setOffEnd(offEnd);
		offDTOs.setOffDiv(offDTO.getOffDiv());
		offDTOs.setSignReason(offDTO.getSignReason());
		offDTOs.setSignStatus("대기"); // 고정값
		offDTOs.setOffDay(offDay);
		offDTOs.setSignRequester(empDTO);
		offDTOs.setSignPayer(payerDTO);
		offDTOs.setRequestDate(requestDateConverted);
		
		log.info("[OffService] applyOff : {}", offDTOs);
		
		offRepository.save(modelMapper.map(offDTOs, Off.class));

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
