package com.insadong.application.employee.dto;

import com.insadong.application.common.entity.EmpAuth;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class EmpDTOImplUS implements UserDetails {

	private Long empCode;
	private String empName;
	private String empId;
	private String empPwd;
	private String empPhone;
	private String empEmail;

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
