package com.insadong.application.training.repository;

import com.insadong.application.common.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, String> {
}
