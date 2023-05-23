package com.insadong.application.Tcalendar.service;

import com.insadong.application.Tcalendar.dto.CalendarDTO;
import com.insadong.application.Tcalendar.entity.Calendar;
import com.insadong.application.Tcalendar.repository.CalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalendarService {

	private final CalendarRepository calendarRepository;
	private final ModelMapper modelMapper;

	public CalendarService(CalendarRepository calendarRepository, ModelMapper modelMapper) {
		this.calendarRepository = calendarRepository;
		this.modelMapper = modelMapper;
	}

	public CalendarDTO viewMyScheduleList(Long empCode) {

		Calendar foundMySchedule = calendarRepository.findByEmployeeEmpCode(empCode);
		log.info("found : {} ", foundMySchedule);

		return null;
	}
}
