package com.insadong.application.employee.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.insadong.application.common.entity.EmpAuth;

import lombok.Data;

@Data
public class EmployeeDTO implements UserDetails {

	private Long empCode;
	private DeptDTO dept;
	private JobDTO job;
	private String empName;
	private String empGender;
	private String empEmail;
	private String empId;
	private String empPwd;
	private String empPhone;
	private Date hireDate;
	private Long offCount;
	private String empState;
	private Date endDate;
	private List<EmpAuth> empAuthList;
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return empPwd;
	}
	@Override
	public String getUsername() {
		return empId;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}
