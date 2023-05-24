package com.insadong.application.off.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.insadong.application.common.entity.EmpAuth;
import com.insadong.application.employee.dto.DeptDTO;
import com.insadong.application.employee.dto.JobDTO;

import lombok.Data;

@Data
public class EmpOffDTO implements UserDetails{
	


		private Long empCode;
		private DeptDTO dept;
		private JobDTO job;
		private String empName;
		private String empId;
		private String empPwd;
		private Double offCount; // 남은 연차
		private String empState;
		private List<EmpAuth> empAuthList;
		private List<OffDTO> offs;
		private Double usedOff; // 사용한 연차

		
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

