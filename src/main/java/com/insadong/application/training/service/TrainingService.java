package com.insadong.application.training.service;

import com.insadong.application.common.entity.Employee;
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
import org.springframework.transaction.annotation.Transactional;

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

	public TrainingDTO viewTraining(Long trainingCode) {

		Training training = trainingRepository.findById(trainingCode).orElseThrow(() -> new IllegalArgumentException("해당 코드로 과정을 조회할 수 없습니다."));

		return modelMapper.map(training, TrainingDTO.class);
	}

	@Transactional
	public void updateTraining(TrainingDTO trainingDTO, Long empCode) {

		Employee originWriter = employeeRepository.findById(trainingDTO.getTrainingWriter().getEmpCode()).orElseThrow(() -> new IllegalArgumentException("해당 코드로 사원을 조회할 수 없습니다."));
		Employee foundEmp = employeeRepository.findById(empCode).orElseThrow(() -> new IllegalArgumentException("해당 코드로 사원을 조회할 수 없습니다."));

		Training training = trainingRepository.findById(trainingDTO.getTrainingCode()).orElseThrow(() -> new IllegalArgumentException("해당 코드로 과정을 조회할 수 없습니다."));

		training.update(
				trainingDTO.getTrainingTitle(),
				trainingDTO.getTrainingQual(),
				trainingDTO.getTrainingKnow(),
				trainingDTO.getTrainingTime(),
				trainingDTO.getTrainingCount(),
				originWriter,
				trainingDTO.getTrainingDate(),
				foundEmp);
	}
}
