package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpJobRepository extends JpaRepository<Job, String> {

    Job findByJobName(String jobName);
}
