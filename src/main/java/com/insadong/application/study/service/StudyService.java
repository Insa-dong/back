package com.insadong.application.study.service;

import com.insadong.application.common.entity.Study;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.study.dto.StudyDTO;
import com.insadong.application.study.repository.StudyInfoRepository;
import com.insadong.application.study.repository.StudyRepository;
import com.insadong.application.training.repository.TrainingRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudyService {

	private final StudyRepository studyRepository;
	private final StudyInfoRepository studyInfoRepository;
	private final EmployeeRepository employeeRepository;
	private final TrainingRepository trainingRepository;
	private final ModelMapper modelMapper;

	public StudyService(StudyRepository studyRepository, StudyInfoRepository studyInfoRepository, EmployeeRepository employeeRepository, TrainingRepository trainingRepository, ModelMapper modelMapper) {
		this.studyRepository = studyRepository;
		this.studyInfoRepository = studyInfoRepository;
		this.employeeRepository = employeeRepository;
		this.trainingRepository = trainingRepository;
		this.modelMapper = modelMapper;
	}

	public Page<StudyDTO> viewStudyList(int page) {

		Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("studyCode").descending());

		Page<Study> foundList = studyRepository.findByStudyDeleteYn(pageable, "N");

		return foundList.map(study -> modelMapper.map(study, StudyDTO.class));
	}
}
