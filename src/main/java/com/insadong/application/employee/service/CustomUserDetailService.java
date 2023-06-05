package com.insadong.application.employee.service;

import com.insadong.application.common.entity.EmpAuth;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import com.insadong.application.employee.entity.EmployeeEntity;
import com.insadong.application.employee.repository.EmpAuthRepository;
import com.insadong.application.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

	private final EmpAuthRepository employeeRepository;
	private final ModelMapper modelMapper;

	public CustomUserDetailService(EmpAuthRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	//@Transactional  // 다시 제외하고 EmployeeRepository에 @EntityGraph empAuthList.auth 추가함
	public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {

		return employeeRepository.findByEmpId(empId).map(user -> addAuthorities(user))
				.orElseThrow(() -> new UserNotFoundException(empId + "를 찾을 수 없습니다."));
	}

	private EmpDTOImplUS addAuthorities(EmployeeEntity employee) {

		EmpDTOImplUS employeeDTO = modelMapper.map(employee, EmpDTOImplUS.class);

		List<GrantedAuthority> authorities = employee.getEmpAuthList().stream()
				.map(EmpAuth::getAuth)
				.map(auth -> new SimpleGrantedAuthority(auth.getAuthName()))
				.collect(Collectors.toList());
		employeeDTO.setAuthorities(authorities);
		return employeeDTO;
	}
}
