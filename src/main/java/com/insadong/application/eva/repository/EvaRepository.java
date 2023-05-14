package com.insadong.application.eva.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Eva;
import com.insadong.application.common.entity.Student;

public interface EvaRepository extends JpaRepository <Eva, Long> {

	Page<Eva> findByStudent(Pageable pageable, Student findStudent);
	
	//@EntityGraph(attributePaths = "student")
	//Page<Eva> findByStudent(Pageable pageable, Student student);


}
