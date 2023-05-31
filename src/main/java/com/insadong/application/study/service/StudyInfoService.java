package com.insadong.application.study.service;

import com.insadong.application.calendar.entity.Calendar;
import com.insadong.application.calendar.repository.CalendarRepository;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.study.dto.PetiteStudyDTO;
import com.insadong.application.study.dto.PetiteStudyInfoDTO;
import com.insadong.application.study.dto.StudyInfoDTO;
import com.insadong.application.study.entity.EmpEntity;
import com.insadong.application.study.entity.StudyInfoEntity;
import com.insadong.application.study.repository.PetiteEmpRepository;
import com.insadong.application.study.repository.StudyInfoRepository;
import com.insadong.application.study.repository.StudyTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class StudyInfoService {
	private final StudyInfoRepository studyInfoRepository;
	private final StudyTimeRepository studyTimeRepository;
	private final PetiteEmpRepository empRepository;
	private final CalendarRepository calendarRepository;
	private final ModelMapper modelMapper;

	public StudyInfoService(StudyInfoRepository studyInfoRepository, StudyTimeRepository studyTimeRepository, PetiteEmpRepository empRepository, CalendarRepository calendarRepository, ModelMapper modelMapper) {
		this.studyInfoRepository = studyInfoRepository;
		this.studyTimeRepository = studyTimeRepository;
		this.empRepository = empRepository;
		this.calendarRepository = calendarRepository;
		this.modelMapper = modelMapper;
	}

	public Page<StudyInfoDTO> viewStudyInfoList(int page) {

		Pageable pageable = PageRequest.of(page - 1, 7, Sort.by("studyInfoCode").descending());
		Page<StudyInfoEntity> foundStudyInfoList = studyInfoRepository.findByStudyStudyDeleteYn(pageable, "N");

		return foundStudyInfoList.map(studyInfo -> modelMapper.map(studyInfo, StudyInfoDTO.class));
	}

	public PetiteStudyInfoDTO viewPetiteStudyInfo(Long studyInfoCode) {
		return modelMapper.map(studyInfoRepository.findById(studyInfoCode).orElseThrow(() -> new IllegalArgumentException("조회 실패 ~")), PetiteStudyInfoDTO.class);
	}

	@Transactional
	public void modifyStudyInfo(PetiteStudyInfoDTO studyInfo, Long studyInfoCode, Long empCode) {
		PetiteStudyDTO study = studyInfo.getStudy();
		EmpEntity empEntity = empRepository.findById(empCode).orElseThrow(() -> new IllegalArgumentException("조회 실패"));
		study.setStudyWriter(modelMapper.map(empEntity, EmployeeDTO.class));

		StudyInfoEntity foundStudyInfo = studyInfoRepository.findById(studyInfoCode).orElseThrow(() -> new IllegalArgumentException("조회 실패~"));
		studyTimeRepository.deleteByStudyCode(foundStudyInfo.getStudy().getStudyCode());


		StudyInfoEntity map = modelMapper.map(studyInfo, StudyInfoEntity.class);
		log.info("map : {} ", map.toString());
		studyInfoRepository.save(map);
	}

	/* 강사 강의 리스트 조회 */
	public Page<StudyInfoDTO> selectTeacherStudyListByEmpCode(int page, Long empCode) {
		log.info("[EmpService] selectTeacherStudyListByEmpCode start ============================== ");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("studyInfoCode").descending());

		Page<StudyInfoEntity> foundList = studyInfoRepository.findByTeacherEmpCode(pageable, empCode);
		System.out.println(empCode);

		return foundList.map(studyInfo -> modelMapper.map(studyInfo, StudyInfoDTO.class));
	}

	@Transactional
	public void insertStudy(PetiteStudyInfoDTO studyInfo, Long empCode) {
		PetiteStudyDTO study = studyInfo.getStudy();
		EmpEntity empEntity = empRepository.findById(empCode).orElseThrow(() -> new IllegalArgumentException("조회 실패"));
		study.setStudyWriter(modelMapper.map(empEntity, EmployeeDTO.class));

		StudyInfoEntity saveEntity = modelMapper.map(studyInfo, StudyInfoEntity.class);
		studyInfoRepository.save(saveEntity);

		Long calEmpCode = studyInfo.getTeacher().getEmpCode();
		Date calStartDate = studyInfo.getStudyInfoStartDate();
		Date calEndDate = studyInfo.getStudyInfoEndDate();
		String calContent = studyInfo.getStudyContent();
		String calTitle = studyInfo.getStudyTitle();

		Calendar calendar = new Calendar();
		EmpEntity emp = new EmpEntity();

		emp.setEmpCode(calEmpCode);
		calendar.setEmployee(emp);
		calendar.setCalStartDate(calStartDate);
		calendar.setCalEndDate(calEndDate);
		calendar.setCalContent(calContent);
		calendar.setCalTitle(calTitle);
		calendar.setCalColor("#FFA9B0");

		calendarRepository.save(calendar);
	}

	@Transactional
	public void deleteStudyByStudyCode(long studyCode) {

		studyInfoRepository.deleteById(studyCode);
	}
}
