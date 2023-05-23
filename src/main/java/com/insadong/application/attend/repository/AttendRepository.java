package com.insadong.application.attend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Attend;
import com.insadong.application.common.entity.Student;
import com.insadong.application.common.entity.Study;

public interface AttendRepository extends JpaRepository<Attend, Long> {

	/* 수강생 출결 조회 */
	Page<Attend> findByStudy(Pageable pageable, Study findStudy);
}
