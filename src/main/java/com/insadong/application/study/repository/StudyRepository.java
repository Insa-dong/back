package com.insadong.application.study.repository;

import com.insadong.application.common.entity.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {

	@EntityGraph(attributePaths = {"training", "training.trainingWriter", "training.trainingModifier", "training.trainingWriter.dept", "training.trainingWriter.job", "training.trainingModifier.dept", "training.trainingModifier.job"})
	Page<Study> findByStudyDeleteYn(Pageable pageable, String n);

}
