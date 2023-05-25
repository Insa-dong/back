package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.EmpAuth;
import com.insadong.application.common.entity.EmpAuthPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpAuthDelRespository extends JpaRepository<EmpAuth, EmpAuthPK> {
}
