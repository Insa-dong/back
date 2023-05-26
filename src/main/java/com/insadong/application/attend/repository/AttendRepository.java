package com.insadong.application.attend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Attend;
import com.insadong.application.common.entity.Student;
import com.insadong.application.common.entity.Study;

public interface AttendRepository extends JpaRepository<Attend, Long> {

	/* 수강생 출결 조회 */
	Page<Attend> findByStudy(Pageable pageable, Study findStudy);

	/* 수강생 상세 조회 */
	Page<Attend> findByStudent(Pageable pageable, Student findStudent);

	/* 수강생 날짜 별 검색*/
	Page<Attend> findByAttendDate(LocalDate attendDate, Pageable pageable);
}
