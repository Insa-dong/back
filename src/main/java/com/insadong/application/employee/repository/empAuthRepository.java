package com.insadong.application.employee.repository;

import com.insadong.application.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface empAuthRepository extends JpaRepository<EmployeeEntity, Long> {


	@EntityGraph(attributePaths = {"empAuthList", "empAuthList.auth"})
	Optional<EmployeeEntity> findByEmpId(String empId);

	Optional<EmployeeEntity> findByEmpNameAndEmpPhone(String empName, String empPhone);
}
