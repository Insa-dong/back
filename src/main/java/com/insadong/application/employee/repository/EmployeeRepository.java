package com.insadong.application.employee.repository;

import com.insadong.application.common.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmpId(String empId);

	/* 1. 전체 구성원 조회 - 페이징
	Page<Employee> findAll(Pageable pageable);*/

	/* 2. 부서별 구성원 조회  - 페이징*/

	/* 3. 부서별 직책별 조회(검색)*/
}
