package com.insadong.application.off.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Off;
import com.insadong.application.off.dto.OffDTO;
import com.insadong.application.off.repository.EmpOffRepository;
import com.insadong.application.off.repository.OffRepository;

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
	public void applyOff(OffDTO offDTO) {
       
		Off off = new Off();

		LocalDate offStart = offDTO.getOffStart();
	    LocalDate offEnd = offDTO.getOffEnd();

	    List<String> signStatusList = Arrays.asList("승인", "대기");

	    // 이미 해당 날짜 범위에 신청된 연차가 있는지 확인
	    if (offRepository.existsByOffStartLessThanEqualAndOffEndGreaterThanEqualAndSignStatusIn(offStart, offEnd, signStatusList)) {
	        throw new RuntimeException("이미 해당 날짜에 연차를 신청했습니다.");
	    }

	    off.setOffStart(offStart);
	    off.setOffEnd(offEnd);
	    off.setOffDiv(offDTO.getOffDiv());
	    off.setSignReason(offDTO.getSignReason());
	    off.setSignStatus(offDTO.getSignStatus());

	    // 연차 일수 계산
	    long days = ChronoUnit.DAYS.between(offStart, offEnd) + 1;

	    // 최대 1일로 제한
	    double offDay = offDTO.getOffDiv().contains("반차") ? 0.5 : Math.min(days, 1);
	    off.setOffDay(offDay);

	    // 신청자 설정
	    Employee requester = empOffRepository.getOne(offDTO.getSignRequester().getEmpCode());
	    off.setSignRequester(requester);

	    // 결재자 = 신청자 팀장
	    Employee payer = empOffRepository.findTeamLeaderByDept(requester.getDept());
	    off.setSignPayer(payer);

	    // 신청일 설정
	    off.setRequestDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

	    offRepository.save(off);
	    
	}


	/* 3. 예정 연차 조회 */
	public Page<OffDTO> myComingUpOffList(Long empCode, int page) {
		PageRequest pageRequest = PageRequest.of(page -1 , 5, Sort.by(Sort.Direction.ASC, "offStart"));
	    Page<Off> offList =  offRepository.findBySignRequester_EmpCode(empCode, pageRequest);
		
	    List<OffDTO> offDTOList = offList.getContent().stream()
	            .map(off -> modelMapper.map(off, OffDTO.class))
	            .collect(Collectors.toList());

	    return new PageImpl<>(offDTOList, pageRequest, offList.getTotalElements());
	    
	}
}







