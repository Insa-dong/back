package com.insadong.application.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Employee;

public interface MemberRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmpId(String empId);

}
