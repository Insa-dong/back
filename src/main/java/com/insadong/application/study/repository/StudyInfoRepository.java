package com.insadong.application.study.repository;

import com.insadong.application.common.entity.StudyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyInfoRepository extends JpaRepository<StudyInfo, Long> {

	@Override
	@EntityGraph(attributePaths = {"study", "study.training", "study.training.trainingWriter", "study.training.trainingModifier", "study.training.trainingWriter.dept", "study.training.trainingWriter.job", "study.training.trainingModifier.dept", "study.training.trainingModifier.job"})
	Optional<StudyInfo> findById(Long study);

	@EntityGraph(attributePaths = {"study", "study.training", "study.training.trainingWriter", "study.training.trainingModifier", "study.training.trainingWriter.dept", "study.training.trainingWriter.job", "study.training.trainingModifier.dept", "study.training.trainingModifier.job"})
	Page<StudyInfo> findByStudyStudyDeleteYn(Pageable pageable, String n);
}
