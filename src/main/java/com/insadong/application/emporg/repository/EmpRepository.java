package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Dept;
import com.insadong.application.common.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee, Long> {

    /*1. 구성원 전체 조회*/
    @EntityGraph(attributePaths={"dept", "job"})
    Page<Employee> findAll(Pageable pageable);

    /*2. 구성원 부서별 조회*/
    @EntityGraph(attributePaths={"dept", "job"})
    Page<Employee> findByDept(Pageable pageable, Dept dept);

    /*3-1. 구성원 이름 검색*/
    @EntityGraph(attributePaths={"dept", "job"})
    Page<Employee> findByEmpName(Pageable pageable, String empName);

    /*3-3. 구성원 직책 검색*/
    @EntityGraph(attributePaths={"dept", "job"})
    Page<Employee> findByJob(Pageable pageable, Job job);

}
