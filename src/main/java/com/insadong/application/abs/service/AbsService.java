package com.insadong.application.abs.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.abs.dto.AbsDTO;
import com.insadong.application.abs.repository.AbsRepository;
import com.insadong.application.common.entity.Abs;
import com.insadong.application.common.entity.Employee;
import com.insadong.application.employee.repository.EmployeeRepository;

@Service
public class AbsService {

	private final AbsRepository absRepository;
	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	public AbsService(AbsRepository absRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.absRepository = absRepository;
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	/* 1. 근태 목록 조회 - 모든 데이터 조회  */
	public Page<AbsDTO> selectAbsServiceListForAdmin(int page) { // 페이징 처리, 엔티티를 가공해서 비영속객체로

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("absDate").descending()); // 몇번째 페이지, 몇개씩, 정렬

		Page<Abs> absList = absRepository.findAll(pageable);

		// modelMapper로 dto로 가공한다. confifuration 밑에 beanconfig 만들어서 modelMapper 등록한다.
		Page<AbsDTO> absDtoList = absList.map(abs -> modelMapper.map(abs, AbsDTO.class));
		// page라는 객체를 map을 가지고 있다.

		return absDtoList;
	}

	/*1-1 내 근태 목록 조회*/
	public Page<AbsDTO> selectAbsListForUser(Employee empCode, int page) {
	    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("absDate").descending());

	    Page<Abs> absList = absRepository.findByEmpCode(empCode.getEmpCode(), pageable);

	    Page<AbsDTO> absDtoList = absList.map(abs -> modelMapper.map(abs, AbsDTO.class));

	    return absDtoList;
	}
	
	/*1-2 팀원 근태 목록 조회*/


	/* 2. 출근 입력  */
	public void checkIn(Long empCode) {
		
		LocalDate today = LocalDate.now();
		

	    // 출근 여부 확인
	    if (absRepository.findByEmpCode_EmpCodeAndAbsDate(empCode, today).isPresent()) {
	        throw new RuntimeException("이미 오늘 출근하셨습니다.");
	    }
	    
	    // Employee 엔티티 찾기
	    Employee employee = employeeRepository.findById(empCode)
	        .orElseThrow(() -> new RuntimeException("사번이 유효하지 않습니다."));

	    // Abs 엔티티 생성
	    Abs abs = new Abs();
	    abs.setEmpCode(employee);
	    abs.setAbsDate(today);
	    abs.setAbsStart(LocalDateTime.now());
	    abs.setAbsEnd(null);

	    // Abs 엔티티 저장
	    absRepository.save(abs);
	}

	/* 3. 퇴근 입력 */
	public void checkOut(Long empCode) {
	    LocalDate today = LocalDate.now();

	    // 출근 여부 확인
	    Abs abs = absRepository.findByEmpCode_EmpCodeAndAbsDate(empCode, today)
	        .orElseThrow(() -> new RuntimeException("출근 기록이 없습니다."));

	    // 이미 퇴근한 경우
	    if (abs.getAbsEnd() != null) {
	        throw new RuntimeException("이미 퇴근하셨습니다.");
	    }

	    // 퇴근 시간 설정
	    abs.setAbsEnd(LocalDateTime.now());

	    // Abs 엔티티 저장
	    absRepository.save(abs);
	}


	/* 4. 근태 날짜 조회 */


	public Page<AbsDTO> selectAbsDateForAdmin(LocalDate absDate, int page) {
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("absStart").descending()); // 몇번째 페이지, 몇개씩, 정렬

		Page<Abs> absList = absRepository.findByAbsDate(absDate, pageable);

		// modelMapper로 dto로 가공한다. confifuration 밑에 beanconfig 만들어서 modelMapper 등록한다.
		Page<AbsDTO> absDtoList = absList.map(abs -> modelMapper.map(abs, AbsDTO.class));
		// page라는 객체를 map을 가지고 있다.

		return absDtoList;
	
		
	
	}

	/* 4. 근태 수정*/

	/*@Transactional
	public void modifyAbs(AbsDTO absDTO) {
	    Abs originAbs = absRepository.findById(absDTO.getAbsCode())
	            .orElseThrow(() -> new IllegalArgumentException("해당 코드의 근태 기록이 없습니다. absCode=" + absDTO.getAbsCode()));

	    // 필요한 필드 값을 가져와서 엔티티의 해당 필드에 직접 할당
	    originAbs.updateAbs(
	    		absDTO.getAbsDate(),
	    		absDTO.getAbsStart(),
	    		absDTO.getAbsEnd()
	   );*/
	    
	    @Transactional
	    public void modifyAbs(AbsDTO absDTO) {
	        Abs originAbs = absRepository.findById(absDTO.getAbsCode())
	                .orElseThrow(() -> new IllegalArgumentException("해당 코드의 근태 기록이 없습니다. absCode=" + absDTO.getAbsCode()));

	        // 필요한 필드 값을 가져와서 엔티티의 해당 필드에 직접 할당
	        originAbs.setAbsDate(absDTO.getAbsDate());
	        originAbs.setAbsStart(absDTO.getAbsStart());
	        originAbs.setAbsEnd(absDTO.getAbsEnd());

	  
	    }
	    		


	    
	}
		
		
	







	
	
	
	
	
	
	
