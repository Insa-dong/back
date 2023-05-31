package com.insadong.application.off.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.insadong.application.common.entity.Dept;
import com.insadong.application.common.entity.EmpAuth;
import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Job;
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
		private Long offCount; // 총 연차(15로 고정)
		private String empState;
		private List<EmpAuth> empAuthList;
		private List<OffDTO> offs;
		private Double usedOff; // 사용한 연차
		private Double remainingOff; // 잔여 연차
		
		
		
		private JobDTO convertToJobDTO(Job job2) {
			JobDTO jobDTO = new JobDTO();
			
			jobDTO.setJobCode(job2.getJobCode());
			jobDTO.setJobName(job2.getJobName());
	       

	        return jobDTO;
		}


		private DeptDTO convertToDeptDTO(Dept dept2) {
			DeptDTO deptDTO = new DeptDTO();
			
	        deptDTO.setDeptCode(dept2.getDeptCode());
	        deptDTO.setDeptName(dept2.getDeptName());
	       

	        return deptDTO;
		}
		
		public void fromEmployee(Employee emp) {
		    this.setEmpCode(emp.getEmpCode());
		    this.setEmpName(emp.getEmpName());
		    this.setEmpId(emp.getEmpId());
		    this.setEmpPwd(emp.getEmpPwd());
		    this.setOffCount(emp.getOffCount());
		    this.setEmpState(emp.getEmpState());
		    this.setEmpAuthList(emp.getEmpAuthList());

		    // You'll need to convert `Dept` and `Job` entities to DTOs here
		    this.setDept(convertToDeptDTO(emp.getDept()));
		    this.setJob(convertToJobDTO(emp.getJob()));
		}

		

	    public String getJobCode() {
	        return this.job.getJobCode();
	    }
	    
	    public String getDeptCode() {
	        return this.dept.getDeptCode();
	    }
	    
	    public String getDeptName() {
	        return this.dept.getDeptName();
	    }


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

