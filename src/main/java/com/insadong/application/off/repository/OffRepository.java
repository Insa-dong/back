package com.insadong.application.off.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Off;
import com.insadong.application.employee.dto.EmployeeDTO;

@Repository
public interface OffRepository extends JpaRepository<Off, Long> {

	/*근태 연차 여부*/

	 Optional<Off> findBySignRequesterAndOffStartLessThanEqualAndOffEndGreaterThanEqualAndSignStatus(
	            Employee empCode,
	            LocalDate offStart, // 변경: LocalDate 타입으로 수정
	            LocalDate offEnd, // 변경: LocalDate 타입으로 수정
	            String signStatus
	    );

	 /* 잔여 연차 계산용*/
	 Page<Off> findAllBySignStatus(String signStatus, Pageable pageable);
	 

	List<Off> findAllBySignRequesterAndSignStatus(EmployeeDTO emp, String signStatus);

}
