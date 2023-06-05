package com.insadong.application.mypage.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.exception.UserNotFoundException;
import com.insadong.application.mypage.repository.MypageRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MypageService {

	private final MypageRepository mypageRepository;
	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;

	public MypageService(MypageRepository mypageRepository, EmployeeRepository employeeRepository,
			PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
		this.mypageRepository = mypageRepository;
		this.employeeRepository = employeeRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
	}

	/* 내정보 조회 */
	public EmployeeDTO selectMypageInfo(Long empCode) {

		Employee employee = mypageRepository.findById(empCode)
				.orElseThrow(() -> new UserNotFoundException(empCode + "를 찾을 수 없습니다."));

		return modelMapper.map(employee, EmployeeDTO.class);
	}

	/* 비밀번호 변경 */
	@Transactional
	public void modifyPwd(EmployeeDTO empDTO, Long empCode) {

		Employee employee = mypageRepository.findById(empCode)
				.orElseThrow(() -> new UserNotFoundException(empCode + "를 찾을 수 없습니다."));

		if (empDTO.getNewPwd().equals(empDTO.getCheckPwd())) {
			String newPwd = passwordEncoder.encode(empDTO.getNewPwd());

			employee.setEmpPwd(newPwd);
		} else {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
	}

	/* 개인정보 변경 */
	@Transactional
	public void modifyPrivacy(EmployeeDTO empDTO, Long empCode) {

		Employee employee = mypageRepository.findById(empCode)
				.orElseThrow(() -> new UserNotFoundException(empCode + "를 찾을 수 없습니다."));

		employee.setEmpPhone(empDTO.getEmpPhone());
		employee.setEmpEmail(empDTO.getEmpEmail());
		employee.setEmpGender(empDTO.getEmpGender());

	}

}
