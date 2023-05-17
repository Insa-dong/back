package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpJobRepository extends JpaRepository<Job, String> {

    @Query("SELECT j.jobCode FROM Job j WHERE j.jobName LIKE %:jobName%")
    List<String> findByJobNameContains(@Param("jobName") String jobName);
}
