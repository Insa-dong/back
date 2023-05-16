package com.insadong.application.study.repository;

import com.insadong.application.study.entity.studyInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyInfoRepository extends JpaRepository<studyInfoEntity, Long> {

	@EntityGraph(attributePaths = {"study", "teacher"})
	Page<studyInfoEntity> findByStudyStudyDeleteYn(Pageable pageable, String n);
}
