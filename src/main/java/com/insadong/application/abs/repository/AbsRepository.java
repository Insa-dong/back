package com.insadong.application.abs.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Abs;
import com.insadong.application.common.entity.Employee;


public interface AbsRepository extends JpaRepository<Abs, Long>{
	
	/* 1. 전체 조회(TEST) */
	
	/* 2. 출퇴근 입력  test*/
	Optional<Abs> findByEmpCodeAndAbsDate(Employee empCode, Date absDate);
	
	

}
