package com.insadong.application.study.repository;

import com.insadong.application.common.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
