package com.insadong.application.studystu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insadong.application.common.entity.Student;
import com.insadong.application.common.entity.StudyStu;
import com.insadong.application.common.entity.StudyStuPK;
import com.insadong.application.common.entity.Training;

public interface StudyStuRepository extends JpaRepository<StudyStu, StudyStuPK>{

	Page<StudyStu> findByStudent(Pageable pageable, Student findStudent);

	/* 수강생 강의 삭제 */
	List<StudyStu> findByStudyStuPKStuCode(Long stuCode);

	 @Query("SELECT t FROM Training t")
	List<Training> findAllTrainings();


}


