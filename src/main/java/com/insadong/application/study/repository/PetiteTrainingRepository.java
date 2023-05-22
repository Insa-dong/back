package com.insadong.application.study.repository;

import com.insadong.application.study.entity.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetiteTrainingRepository extends JpaRepository<TrainingEntity, Long> {
}
