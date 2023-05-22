package com.insadong.application.study.repository;

import com.insadong.application.study.entity.StudyInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyInfoRepository extends JpaRepository<StudyInfoEntity, Long> {

	@EntityGraph(attributePaths = {"study", "teacher"})
	Page<StudyInfoEntity> findByStudyStudyDeleteYn(Pageable pageable, String n);

	Page<StudyInfoEntity> findByTeacherEmpCode(Pageable pageable, Long empCode);
}
