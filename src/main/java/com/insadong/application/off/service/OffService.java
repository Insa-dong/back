package com.insadong.application.off.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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
	
	public OffService(OffRepository offRepository, EmpOffRepository empOffRepository) {
		this.offRepository = offRepository;
		this.empOffRepository = empOffRepository;
	}


	/* 연차 신청 */
	public void applyOff(OffDTO offDTO) {
       
		Off off = new Off();

        off.setOffStart(offDTO.getOffStart());
        off.setOffEnd(offDTO.getOffEnd());
        off.setOffDiv(offDTO.getOffDiv());
        off.setSignReason(offDTO.getSignReason());
        off.setSignStatus(offDTO.getSignStatus());

        // 연차 일수 계산 : 
        long days = ChronoUnit.DAYS.between(offDTO.getOffStart(), offDTO.getOffEnd()) + 1;
        off.setOffDay(offDTO.getOffDiv().contains("반차") ? 0.5 : days); // 반차인 경우 무조건 0.5일

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
}







