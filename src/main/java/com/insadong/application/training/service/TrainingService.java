package com.insadong.application.training.service;

import com.insadong.application.common.entity.Training;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.training.dto.TrainingDTO;
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
public class TrainingService {

	private final TrainingRepository trainingRepository;
	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	public TrainingService(TrainingRepository trainingRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.trainingRepository = trainingRepository;
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	public Page<TrainingDTO> viewTrainingList(int page) {

		Pageable pageable = PageRequest.of(page - 1, 8, Sort.by("trainingCode").descending());

		Page<Training> foundList = trainingRepository.findAll(pageable);

		return foundList.map(training -> modelMapper.map(training, TrainingDTO.class));
	}
}
