package com.insadong.application.employee.service;

import com.insadong.application.common.entity.EmpAuth;
import com.insadong.application.common.entity.Employee;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.employee.repository.EmployeeRepository;
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

	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	public CustomUserDetailService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {

		log.info("[CustomUserDetailService] loadUserByUsername start ======================");
		log.info("[CustomUserDetailService] empId : {}", empId);

		return employeeRepository.findByEmpId(empId).map(user -> addAuthorities(user))
				.orElseThrow(() -> new UserNotFoundException(empId + "를 찾을 수 없습니다."));
	}

	private EmployeeDTO addAuthorities(Employee employee) {

		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

		  List<GrantedAuthority> authorities = employee.getEmpAuthList().stream()
			        .map(EmpAuth::getAuth)
			        .map(auth -> new SimpleGrantedAuthority(auth.getAuthName()))
			        .collect(Collectors.toList());
			    employeeDTO.setAuthorities(authorities);
		return employeeDTO;
	}
}
