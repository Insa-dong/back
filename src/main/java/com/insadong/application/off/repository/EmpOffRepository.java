package com.insadong.application.off.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insadong.application.common.entity.Dept;
import com.insadong.application.common.entity.Employee;

@Repository
public interface EmpOffRepository extends JpaRepository<Employee, Long>{

	
	/*연차 신청*/
	@Query("SELECT e FROM Employee e WHERE e.dept = :dept AND e.job.jobCode = 'JB0002'")
    Employee findTeamLeaderByDept(@Param("dept") Dept dept);
	
	@EntityGraph(attributePaths = {"offs"})
	@Query("SELECT e FROM Employee e WHERE e.empState = '재직중'")
    Page<Employee> findAllActiveEmployeesWithOff(Pageable pageable);
	
	

}
