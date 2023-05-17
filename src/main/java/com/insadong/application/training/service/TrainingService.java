package com.insadong.application.training.service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Training;
import com.insadong.application.employee.dto.EmployeeDTO;
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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

		Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("trainingCode").descending());

		Page<Training> foundList = trainingRepository.findByTrainingDeleteYn(pageable, "N");

		return foundList.map(training -> modelMapper.map(training, TrainingDTO.class));
	}

	public TrainingDTO viewTraining(Long trainingCode) {

		Training training = trainingRepository.findById(trainingCode).orElseThrow(() -> new IllegalArgumentException("해당 코드로 과정을 조회할 수 없습니다."));

		return modelMapper.map(training, TrainingDTO.class);
	}

	public List<com.insadong.application.study.dto.TrainingDTO> viewTrainingTitleList() {

		return trainingRepository.findAll().stream().map(training -> modelMapper.map(training, com.insadong.application.study.dto.TrainingDTO.class)).collect(Collectors.toList());
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
				foundEmp,
				trainingDTO.getTrainingDeleteYn());
	}

	@Transactional
	public void insertTraining(TrainingDTO trainingDTO, long empCode) {

		Employee employee = employeeRepository.findById(empCode).orElseThrow(() -> new IllegalArgumentException("해당 코드로 사원을 조회할 수 없습니다."));
		EmployeeDTO empDTO = modelMapper.map(employee, EmployeeDTO.class);
		trainingDTO.setTrainingWriter(empDTO);

		trainingRepository.save(modelMapper.map(trainingDTO, Training.class));
	}

	@Transactional
	public void updateDeleteYN(Long trainingCode, Long empCode) {

		Training foundTraining = trainingRepository.findById(trainingCode).orElseThrow(() -> new IllegalArgumentException("해당 코드로 과정을 조회할 수 없습니다."));
		Employee foundEmp = employeeRepository.findById(empCode).orElseThrow(() -> new IllegalArgumentException("해당 코드로 사원을 조회할 수 없습니다."));

		foundTraining.setTrainingDeleteYn("Y");
		foundTraining.setTrainingModifier(foundEmp);
		foundTraining.setTrainingUpdate(new Date());
	}

	public Page<TrainingDTO> selectTrainingListByTrainingTitle(String trainingTitle, int page) {

		Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("trainingCode").descending());
		Page<Training> searchList = trainingRepository.findByTrainingTitleContainsAndTrainingDeleteYn(pageable, trainingTitle, "N");

		return searchList.map(training -> modelMapper.map(training, TrainingDTO.class));
	}

	public Page<TrainingDTO> selectTrainingListByTrainingCount(String trainingCount, int page) {

		Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("trainingCode").descending());
		Page<Training> searchList = trainingRepository.findByTrainingCountContainsAndTrainingDeleteYn(pageable, trainingCount, "N");

		return searchList.map(training -> modelMapper.map(training, TrainingDTO.class));
	}
}
