package com.insadong.application.Tcalendar.repository;

import com.insadong.application.Tcalendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {


	Calendar findByEmployeeEmpCode(Long empCode);
}
