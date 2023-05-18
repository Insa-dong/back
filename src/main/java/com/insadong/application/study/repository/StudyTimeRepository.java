package com.insadong.application.study.repository;

import com.insadong.application.common.entity.StudyTime;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyTimeRepository extends JpaRepository<StudyTime, Long> {

	@EntityGraph(attributePaths = "study")
	List<StudyTime> findByStudyCode(Long studyCode);
}
