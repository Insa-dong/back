package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpDeptRepository extends JpaRepository<Dept, String> {

    @Query("SELECT d.deptCode FROM Dept d WHERE d.deptName LIKE %:deptName%")
    List<String> findByDeptNameContains(@Param("deptName") String deptName);
}
