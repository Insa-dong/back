package com.insadong.application.employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.employee.dto.TokenDTO;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.exception.LoginFailedException;
import com.insadong.application.jwt.TokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {

	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;

	public AuthService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper,
			TokenProvider tokenProvider) {
		this.employeeRepository = employeeRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.tokenProvider = tokenProvider;
	}

	public TokenDTO login(EmployeeDTO employeeDTO) {

		log.info("[AuthService] login start ======================================");
		log.info("[AuthService] employeeDTO : {}", employeeDTO);

		// 1. 아이디로 DB에서 해당 유저가 있는지 조회
		Employee employee = employeeRepository.findByEmpId(employeeDTO.getEmpId())
				.orElseThrow(() -> new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다."));

		// 2. 비밀번호 매칭 확인
		if (!passwordEncoder.matches(employeeDTO.getEmpPwd(), employee.getEmpPwd())) {
			throw new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다.");
		}

		// 3. 토큰 발급
		TokenDTO tokenDTO = tokenProvider.generateTokenDTO(modelMapper.map(employee, EmployeeDTO.class));

		log.info("[AuthService] tokenDTO : {}", tokenDTO);

		log.info("[AuthService] login end ======================================");
		
		return tokenDTO;
	}

}
