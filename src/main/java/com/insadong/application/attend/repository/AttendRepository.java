package com.insadong.application.attend.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insadong.application.common.entity.Attend;
import com.insadong.application.common.entity.Student;
import com.insadong.application.common.entity.Study;

public interface AttendRepository extends JpaRepository<Attend, Long> {

	/* 수강생 출결 조회 */
	// Page<Attend> findByStudy(Pageable pageable, Study findStudy);
	List<Attend> findByStudy(Study findStudy);

	/* 수강생 상세 조회 */
	Page<Attend> findByStudent(Pageable pageable, Student findStudent);

	/* 수강생 날짜 별 검색*/
	// Page<Attend> findByAttendDate(Date attendDate, Pageable pageable);

	List<Attend> findByAttendDate(Date attendDate);
	
	/* 출석 삭제*/
	List<Attend> findByStudyStudyCode(Long studyCode);

	/* 강의 삭제 시 출결 삭제 */
	@Query("SELECT a FROM Attend a JOIN a.study s JOIN a.student stu WHERE s.studyCode = :studyCode AND stu.stuCode = :stuCode")
	List<Attend> findByStudyCodeAndStudentCode(@Param("studyCode") Long studyCode, @Param("stuCode") Long stuCode);

}
