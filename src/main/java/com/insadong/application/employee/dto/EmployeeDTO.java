package com.insadong.application.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.common.entity.EmpAuth;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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
	//@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	private Long offCount;
	private String empState;
	@DateTimeFormat(pattern = "yy/MM/dd")
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
