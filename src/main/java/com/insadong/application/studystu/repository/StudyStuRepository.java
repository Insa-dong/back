package com.insadong.application.studystu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Student;
import com.insadong.application.common.entity.StudyStu;

public interface StudyStuRepository extends JpaRepository<StudyStu, Long>{

	Page<StudyStu> findByStudent(Pageable pageable, Student findStudent);

}
