package com.insadong.application.calendar.repository;

import com.insadong.application.calendar.entity.Calendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {


	@Query("select c from Calendar c where c.employee.empCode = :empCode")
	List<Calendar> findByEmployee(Long empCode);

	Page<Calendar> findByEmployeeEmpCode(Long empCode, Pageable pageable);

}
