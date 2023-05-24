package com.insadong.application.Tcalendar.repository;

import com.insadong.application.Tcalendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {


	List<Calendar> findByEmployeeEmpCode(Long empCode);

}
