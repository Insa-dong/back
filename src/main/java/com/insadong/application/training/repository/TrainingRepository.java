package com.insadong.application.training.repository;

import com.insadong.application.common.entity.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {

	@EntityGraph(attributePaths = "trainingWriter")
	Page<Training> findByTrainingDeleteYn(Pageable pageable, String trainingDeleteYn);

	@EntityGraph(attributePaths = {"trainingWriter", "trainingModifier"})
	Page<Training> findByTrainingTitleContainsAndTrainingDeleteYn(Pageable pageable, String trainingTitle, String n);

	@EntityGraph(attributePaths = {"trainingWriter", "trainingModifier"})
	Page<Training> findByTrainingCountContainsAndTrainingDeleteYn(Pageable pageable, String trainingCount, String n);
}
