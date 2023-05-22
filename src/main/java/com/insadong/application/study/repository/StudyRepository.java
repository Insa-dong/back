package com.insadong.application.study.repository;

import com.insadong.application.common.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {

	@Query(value = "SELECT nvl(max(s.studyCount), 0) from Study s right Join s.training t where t.trainingCode IN :trainingCodeList group by t.trainingCode order by t.trainingCode DESC")
	List<Long> findByTrainingCodes(List<Long> trainingCodeList);

	@Query(value = "SELECT MAX(s.studyCount) FROM Study s WHERE s.training.trainingCode = :trainingCode")
	Long findByTrainingCode(Long trainingCode);
}
