package com.insadong.application.study.repository;

import com.insadong.application.study.entity.StudyInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyInfoRepository extends JpaRepository<StudyInfoEntity, Long> {

	@EntityGraph(attributePaths = {"study", "teacher"})
	Page<StudyInfoEntity> findByStudyStudyDeleteYn(Pageable pageable, String n);

	Page<StudyInfoEntity> findByTeacherEmpCode(Pageable pageable, Long empCode);

	Page<StudyInfoEntity> findByStudyTrainingTrainingTitleContainingIgnoreCase(String search, Pageable pageable);

	Page<StudyInfoEntity> findByTeacherEmpNameContaining(String search, Pageable pageable);

	Page<StudyInfoEntity> findByStudyTitleContainingIgnoreCase(String search, Pageable pageable);

	@Query(value = "select info.study.studyCode from StudyInfoEntity info where info.study.studyCode in :studyCodeList")
	List<Long> findAllByStudyStudyCode(List<Long> studyCodeList);
}
