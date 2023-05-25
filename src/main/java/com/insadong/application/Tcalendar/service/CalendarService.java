package com.insadong.application.Tcalendar.service;

import com.insadong.application.Tcalendar.dto.CalendarDTO;
import com.insadong.application.Tcalendar.entity.Calendar;
import com.insadong.application.Tcalendar.repository.CalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CalendarService {

	private final CalendarRepository calendarRepository;
	private final ModelMapper modelMapper;

	public CalendarService(CalendarRepository calendarRepository, ModelMapper modelMapper) {
		this.calendarRepository = calendarRepository;
		this.modelMapper = modelMapper;
	}

	public List<CalendarDTO> viewMyScheduleList(Long empCode) {

		return calendarRepository.findByEmployeeEmpCode(empCode).stream().map(schedule -> modelMapper.map(schedule, CalendarDTO.class)).collect(Collectors.toList());
	}

	@Transactional
	public void updateMyCal(List<CalendarDTO> calendar) {
		List<Calendar> calList = calendar.stream().map(cal -> modelMapper.map(cal, Calendar.class)).collect(Collectors.toList());
		List<Long> codeList = calList.stream().map(Calendar::getCalCode).collect(Collectors.toList());

		List<Calendar> foundList = calendarRepository.findAllById(codeList);
		log.info("codeList : {} ", codeList);
		log.info("foundList : {} ", foundList);
		calendarRepository.saveAll(calList);
	}

	@Transactional
	public void updateMyCalInfo(CalendarDTO calendar) {

		Calendar foundCal = calendarRepository.findById(calendar.getCalCode()).orElseThrow(() -> new IllegalStateException("조회 실패"));

		foundCal.update(calendar.getCalTitle(), calendar.getCalStartDate(), calendar.getCalEndDate());
	}
}

