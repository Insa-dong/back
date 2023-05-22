package com.insadong.application.study.repository;

import com.insadong.application.study.entity.EmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetiteEmpRepository extends JpaRepository<EmpEntity, Long> {
	
}
