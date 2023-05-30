package com.insadong.application.employee.repository;

import com.insadong.application.common.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@EntityGraph(attributePaths = {"dept", "job", "empAuthList", "empAuthList.auth"})
	Optional<Employee> findByEmpId(String empId);

	Optional<Employee> findByEmpNameAndEmpPhone(String empName, String empPhone);

	Employee findByEmpIdAndEmpEmail(String empId, String empEmail);

	List<Employee> findByEmpNameContains(String searchKeyword);

	/* 팀원 연차 조회(팀원)*/
	List<Employee> findByDeptDeptCode(String deptCode);

}
