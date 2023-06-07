package com.insadong.application.employee.service;

import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.employee.dto.TokenDTO;
import com.insadong.application.employee.entity.EmployeeEntity;
import com.insadong.application.employee.repository.EmpAuthRepository;
import com.insadong.application.exception.IdsearchFailedException;
import com.insadong.application.exception.LoginFailedException;
import com.insadong.application.exception.UserNotFoundException;
import com.insadong.application.jwt.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

	private final EmpAuthRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;

	public AuthService(EmpAuthRepository employeeRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper,
					   TokenProvider tokenProvider) {
		this.employeeRepository = employeeRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.tokenProvider = tokenProvider;
	}

	/* 로그인 */
	public TokenDTO login(EmpDTOImplUS employeeDTO) {

		// 1. 아이디로 DB에서 해당 유저가 있는지 조회
		EmployeeEntity employee = employeeRepository.findByEmpId(employeeDTO.getEmpId())
				.orElseThrow(() -> new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다."));

		// 2. 비밀번호 매칭 확인
		if (!passwordEncoder.matches(employeeDTO.getEmpPwd(), employee.getEmpPwd())) {
			throw new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다.");
		}

		// 3. 토큰 발급
		TokenDTO tokenDTO = tokenProvider.generateTokenDTO(modelMapper.map(employee, EmpDTOImplUS.class));

		return tokenDTO;
	}

	/* 아이디 찾기 */
	public EmpDTOImplUS idSearch(EmpDTOImplUS employeeDTO) {

		EmployeeEntity employee = employeeRepository.findByEmpNameAndEmpPhone(employeeDTO.getEmpName(), employeeDTO.getEmpPhone())
				.orElseThrow(() -> new IdsearchFailedException("입력하신 정보와 일치하는 아이디가 존재하지 않습니다."));


		return modelMapper.map(employee, EmpDTOImplUS.class);
	}

	/* 비밀번호 찾기 */
	public EmpDTOImplUS findById(EmpDTOImplUS employeeDTO) {

		EmployeeEntity employee = employeeRepository.findByEmpId(employeeDTO.getEmpId())
				.orElseThrow(() -> new UserNotFoundException("해당 아이디와 일치하는 사용자가 없습니다."));

		if (employee.getEmpEmail().equals(employeeDTO.getEmpEmail())) {
			return modelMapper.map(employee, EmpDTOImplUS.class);
		} else {
			return null;
		}

	}

}
