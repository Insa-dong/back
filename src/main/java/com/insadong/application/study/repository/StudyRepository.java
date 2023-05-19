package com.insadong.application.study.repository;

import com.insadong.application.common.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {

	@Query(value = "SELECT s.studyCount FROM Study s WHERE s.training.trainingCode IN :trainingCodeList AND s.studyCount = " +
			"(SELECT MAX(a.studyCount) FROM Study a WHERE a.training.trainingCode = s.training.trainingCode) order by s.training.trainingCode")
	List<Long> findByTrainingCodes(List<Long> trainingCodeList);

	@Query(value = "SELECT MAX(s.studyCount) FROM Study s WHERE s.training.trainingCode = :trainingCode")
	Long findByTrainingCode(Long trainingCode);
}
