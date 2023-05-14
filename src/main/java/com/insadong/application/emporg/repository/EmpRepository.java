package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Student;
import com.insadong.application.emporg.entity.Emp;
import com.insadong.application.emporg.entity.EmpDept;
import com.insadong.application.emporg.entity.EmpJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Long> {

    /*1. 구성원 전체 조회*/
    @EntityGraph(attributePaths={"dept", "job"})
    Page<Emp> findAll(Pageable pageable);

    /*2. 구성원 부서별 조회*/
    @EntityGraph(attributePaths={"dept", "job"})
    Page<Emp> findByDept(Pageable pageable, EmpDept dept);

    /*3. 구성원 검색*/
    @EntityGraph(attributePaths={"dept", "job"})
    Page<Emp> findByEmpNameContainsAndDeptAndJob(String empName, EmpDept dept, EmpJob job, Pageable pageable);

}
