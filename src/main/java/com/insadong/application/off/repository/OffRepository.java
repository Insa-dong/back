package com.insadong.application.off.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Off;
import com.insadong.application.off.dto.OffDTO;


@Repository
public interface OffRepository extends JpaRepository<Off, Long> {

	/* 근태 연차 여부 */

	Optional<Off> findBySignRequesterAndOffStartLessThanEqualAndOffEndGreaterThanEqualAndSignStatus(Employee empCode,
			LocalDate offStart, LocalDate offEnd, String signStatus);

	/* 연차 중복 신청 방지 */
	boolean existsByOffStartLessThanEqualAndOffEndGreaterThanEqualAndSignStatusIn(LocalDate offStart, LocalDate offEnd,
			List<String> signStatusList);


	/* 연차 현황 조회 */
	List<Off> findBySignRequester_EmpCodeAndSignStatus(Long empCode, String signStatuses);

	/* 내 연차 조회 */
	List<Off> findBySignRequester_EmpCodeOrSignPayer_EmpCode(Long signRequester, Long signPayer, Sort by);
	
	/* 연차 상세 조회*/
	Off findBySignCodeAndSignRequester(Long signCode, Employee signRequester);
	
 
	/* 연차 신청 조회 */

	Page<Off> findBySignPayer_EmpCode(Long empCode, PageRequest pageRequest);
	Page<Off> findBySignRequester_EmpNameContains(Pageable pageable, String empName);
	Page<Off> findBySignStatus(Pageable pageable, String searchKeyword);

	/* 연차 승인 처리*/
	Off findBySignPayer_EmpCode(Long empCode);

	

	





	

	

	

	

	

}
