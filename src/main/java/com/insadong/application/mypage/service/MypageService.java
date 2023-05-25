package com.insadong.application.mypage.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.exception.UserNotFoundException;
import com.insadong.application.mypage.repository.MypageRepository;

@Service
public class MypageService {

	private final MypageRepository mypageRepository;
	private final ModelMapper modelMapper;

	public MypageService(MypageRepository mypageRepository, ModelMapper modelMapper) {
		this.mypageRepository = mypageRepository;
		this.modelMapper = modelMapper;
	}

	public EmployeeDTO selectMypageInfo(Long empCode) {
		
		Employee employee = mypageRepository.findById(empCode)
				.orElseThrow(() -> new UserNotFoundException(empCode + "를 찾을 수 없습니다."));
		
		return modelMapper.map(employee, EmployeeDTO.class);
	}

}
