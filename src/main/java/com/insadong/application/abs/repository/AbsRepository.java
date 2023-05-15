package com.insadong.application.abs.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Abs;


public interface AbsRepository extends JpaRepository<Abs, Long>{
	
	/* 1. 전체 조회(TEST) */
	
	/* 2. 출퇴근 입력  test*/
    Optional<Abs> findByEmpCode_EmpCodeAndAbsDate(Long empCode, LocalDate absDate);
    



}
	
	


