package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpDeptRepository extends JpaRepository<Dept, String> {

    Dept findByDeptName(String deptName);
}
