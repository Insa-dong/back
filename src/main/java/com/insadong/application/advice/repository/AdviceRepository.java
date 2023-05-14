package com.insadong.application.advice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Advice;
import com.insadong.application.common.entity.Student;

public interface AdviceRepository extends JpaRepository<Advice, Long> {

	Page<Advice> findByStudent(Pageable pageable, Student student);


//	@EntityGraph(attributePaths = {"student"})
//	Page<Advice> findByStudent(Pageable pageable, Student student);

}
