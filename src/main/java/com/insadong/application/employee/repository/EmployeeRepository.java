package com.insadong.application.employee.repository;

import com.insadong.application.common.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@EntityGraph(attributePaths = {"dept", "job", "empAuthList"})
	Optional<Employee> findByEmpId(String empId);


}
