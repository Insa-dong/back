package com.insadong.application.off.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insadong.application.common.entity.Dept;

@Repository
public interface DeptOffRepository extends JpaRepository<Dept, String>{

}
