package com.insadong.application.abs.service;


import java.text.SimpleDateFormat;
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



@Service
public class AbsService {

	private final AbsRepository absRepository;
	private final ModelMapper modelMapper;
	
	public AbsService(AbsRepository absRepository, ModelMapper modelMapper ) {
		this.absRepository = absRepository;
		this.modelMapper = modelMapper;
}
	
	/* 1. 근태 목록 조회 - 모든 데이터 조회 (test용)*/
	public Page<AbsDTO> selectAbsServiceListForAdmin(int page) { //페이징 처리, 엔티티를 가공해서 비영속객체로
		
		Pageable pageable = PageRequest.of(page -1 , 10, Sort.by("absCode").descending()); //몇번째 페이지, 몇개씩, 정렬
		
		Page<Abs> absList = absRepository.findAll(pageable);
		
		//modelMapper로 dto로 가공한다. confifuration 밑에 beanconfig 만들어서 modelMapper 등록한다.
		Page<AbsDTO> absDtoList = absList.map(abs -> modelMapper.map(abs, AbsDTO.class));
		//page라는 객체를 map을 가지고 있다. 
		
		return absDtoList;
	}

	/* 2. 근태 입력 (test용)*/
//	@Transactional
//	public void checkIn(AbsDTO absDTO, String empId) {
//	    Employee emp = employeeService.findByEmpId(empId);
//	    Abs abs = new Abs();
//	    abs.setEmpCode(emp);
//	    abs.setAbsDate(new Date());
//	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//	    abs.setAbsStart(formatter.format(new Date()));
//	    absRepository.save(abs);
//	}
		
	}
	
	
	
	
	
	
	
