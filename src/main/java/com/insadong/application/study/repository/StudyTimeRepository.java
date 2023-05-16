package com.insadong.application.study.repository;

import com.insadong.application.common.entity.StudyTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyTimeRepository extends JpaRepository<StudyTime, Long> {


//	List<StudyTime> findByStudyStudyCode(Long studyCode);
}
