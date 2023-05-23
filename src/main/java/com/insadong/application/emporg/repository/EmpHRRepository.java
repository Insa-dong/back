package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.HR;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpHRRepository extends JpaRepository<HR, Long> {
    Page<HR> findByEmployee(Pageable pageable, Employee employee);
}
