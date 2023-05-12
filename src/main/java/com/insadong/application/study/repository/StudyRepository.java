package com.insadong.application.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
