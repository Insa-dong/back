package com.insadong.application.student.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insadong.application.common.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	/* 1. 수강생 전체 조회 */
	Page<Student> findAll(Pageable pageable);

	/* 3. 수강생 상세 보기 */
	@Query("SELECT s FROM Student s WHERE s.stuCode = : stuCode")
	Optional<Student> findByStuCode(@Param("stuCode") Long stuCode);
}
