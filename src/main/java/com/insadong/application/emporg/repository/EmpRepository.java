package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Student;
import com.insadong.application.emporg.entity.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Long> {

    /*1. 구성원 전체 조회
    * findAll*/
    Page<Emp> findAll(Pageable pageable);
}
