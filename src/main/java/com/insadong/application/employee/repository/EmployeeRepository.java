package com.insadong.application.employee.repository;

import java.util.Optional;

import com.insadong.application.common.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@EntityGraph(attributePaths = {"dept", "job", "empAuthList"})
	Optional<Employee> findByEmpId(String empId);

	Optional<Employee> findByEmpNameAndEmpPhone(String empName, String empPhone);

	Employee findByEmpIdAndEmpEmail(String empId, String empEmail);



	



}
