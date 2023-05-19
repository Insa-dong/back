package com.insadong.application.study.repository;

import com.insadong.application.common.entity.Study;
import com.insadong.application.training.dto.StudyCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {

	@Query("select count(s.training.trainingCode),s.training.trainingCode from Study s where count(s.training.trainingCode) =:trainingCount group by s.training.trainingCode")
	List<StudyCountDTO> countByTraining(long trainingCount);
}
