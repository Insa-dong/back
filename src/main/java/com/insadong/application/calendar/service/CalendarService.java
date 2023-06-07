package com.insadong.application.calendar.service;

import com.insadong.application.calendar.dto.CalendarDTO;
import com.insadong.application.calendar.dto.PetiteEmpDTO;
import com.insadong.application.calendar.entity.Calendar;
import com.insadong.application.calendar.repository.CalendarRepository;
import com.insadong.application.employee.dto.EmpDTOImplUS;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

		return calendarRepository.findByEmployee(empCode).stream().map(schedule -> modelMapper.map(schedule, CalendarDTO.class)).collect(Collectors.toList());
	}

	public Page<CalendarDTO> viewMyPagingScheduleList(Long empCode, int page, String sort) {

		if (sort.equals("calEndDate")) {
			Pageable pageable = PageRequest.of(page - 1, 8, Sort.by(sort).descending());
			return calendarRepository.findByEmployeeEmpCode(empCode, pageable).map(Calendar -> modelMapper.map(Calendar, CalendarDTO.class));
		} else {
			Pageable pageable = PageRequest.of(page - 1, 8, Sort.by(sort));
			return calendarRepository.findByEmployeeEmpCode(empCode, pageable).map(Calendar -> modelMapper.map(Calendar, CalendarDTO.class));
		}
	}

	@Transactional
	public void updateMyCal(List<CalendarDTO> calendar) {
		List<Calendar> calList = calendar.stream().map(cal -> modelMapper.map(cal, Calendar.class)).collect(Collectors.toList());
		List<Long> codeList = calList.stream().map(Calendar::getCalCode).collect(Collectors.toList());

		List<Calendar> foundList = calendarRepository.findAllById(codeList);
		calendarRepository.saveAll(calList);
	}

	@Transactional
	public void updateMyCalInfo(CalendarDTO calendar) {

		Calendar foundCal = calendarRepository.findById(calendar.getCalCode()).orElseThrow(() -> new IllegalStateException("조회 실패"));

		foundCal.update(calendar.getCalTitle(), calendar.getCalContent(), calendar.getCalStartDate(), calendar.getCalEndDate(), calendar.getCalColor());
	}

	@Transactional
	public void registerMySchedule(CalendarDTO calendar, EmpDTOImplUS writer) {

		PetiteEmpDTO employee = new PetiteEmpDTO();
		employee.setEmpCode(writer.getEmpCode());
		employee.setEmpName(writer.getEmpName());

		calendar.setEmployee(employee);

		calendarRepository.save(modelMapper.map(calendar, Calendar.class));
	}

	@Transactional
	public void deleteMySchedule(List<Long> codeList) {

		calendarRepository.deleteAllById(codeList);
	}
}

