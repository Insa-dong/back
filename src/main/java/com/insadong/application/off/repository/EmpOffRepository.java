package com.insadong.application.off.repository;


import com.insadong.application.common.entity.Dept;
import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Off;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpOffRepository extends JpaRepository<Employee, Long>{

	/*연차 신청*/
	@Query("SELECT e FROM Employee e WHERE e.dept = :dept AND e.job.jobCode = 'JB0002'")
    Employee findTeamLeaderByDept(@Param("dept") Dept dept);


	
	/* 팀원 연차 현황 조회 */
	
	/*@Query("SELECT e FROM Employee e WHERE e.dept.deptCode = :deptCode")
	List<Employee> findEmployeesByDeptCode(@Param("deptCode") String deptCode);*/

	List<Employee> findByDept_DeptCode(String deptCode);

	Page<Employee> findByEmpNameContains(Pageable pageable, String searchKeyword);

	

	

	

	


	

}
