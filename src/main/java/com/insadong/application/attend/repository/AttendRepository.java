package com.insadong.application.attend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Attend;

public interface AttendRepository extends JpaRepository<Attend, Long> {

}
