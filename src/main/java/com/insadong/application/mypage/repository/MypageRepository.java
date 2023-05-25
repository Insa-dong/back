package com.insadong.application.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insadong.application.common.entity.Employee;

public interface MypageRepository extends JpaRepository<Employee, Long>{

}
