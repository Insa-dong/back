package com.insadong.application.studyInfo.repository;

import com.insadong.application.common.entity.StudyInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyInfoRepository extends JpaRepository<StudyInfo, Long> {

	@Override
	@EntityGraph(attributePaths = {"studyCode", "studyCode.training", "studyCode.training.trainingWriter", "studyCode.training.trainingModifier", "studyCode.training.trainingWriter.dept", "studyCode.training.trainingWriter.job", "studyCode.training.trainingModifier.dept", "studyCode.training.trainingModifier.job", "studyCode.training.trainingModifier.empAuthList", "studyCode.training.trainingModifier.empAuthList"})
	Optional<StudyInfo> findById(Long studyCode);
}
