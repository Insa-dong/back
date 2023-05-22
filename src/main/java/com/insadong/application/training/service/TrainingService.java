package com.insadong.application.training.service;

import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Training;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.study.dto.EmpDTO;
import com.insadong.application.study.dto.PetiteTrainingDTO;
import com.insadong.application.study.repository.StudyRepository;
import com.insadong.application.training.dto.TrainingDTO;
import com.insadong.application.training.repository.TrainingRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrainingService {

	private final TrainingRepository trainingRepository;
	private final EmployeeRepository employeeRepository;
	private final StudyRepository studyRepository;
	private final ModelMapper modelMapper;

	public TrainingService(TrainingRepository trainingRepository, EmployeeRepository employeeRepository, StudyRepository studyRepository, ModelMapper modelMapper) {
		this.trainingRepository = trainingRepository;
		this.employeeRepository = employeeRepository;
		this.studyRepository = studyRepository;
		this.modelMapper = modelMapper;
	}

	public Page<TrainingDTO> viewTrainingList(int page) {

		Pageable pageable = PageRequest.of(page - 1, 7, Sort.by("trainingCode").descending());

		Page<Training> foundList = trainingRepository.findByTrainingDeleteYn(pageable, "N");
		Page<TrainingDTO> foundDTOList = foundList.map(training -> modelMapper.map(training, TrainingDTO.class));
		List<Long> trainingCodeList = foundList.map(Training::getTrainingCode).toList();
		List<Long> foundCountList = studyRepository.findByTrainingCodes(trainingCodeList);

		List<TrainingDTO> list = foundDTOList.toList();

		for (int i = 0; i < foundCountList.size(); i++) {
			list.get(i).setStudyCount(foundCountList.get(i));
		}

		return new PageImpl<>(list, pageable, trainingRepository.countByTraining());
	}

	public TrainingDTO viewTraining(Long trainingCode) {

		Training training = trainingRepository.findById(trainingCode).orElseThrow(() -> new IllegalArgumentException("해당 코드로 과정을 조회할 수 없습니다."));
		TrainingDTO trainingDTO = modelMapper.map(training, TrainingDTO.class);
		trainingDTO.setStudyCount(studyRepository.findByTrainingCode(training.getTrainingCode()));
		return trainingDTO;
	}

	public List<PetiteTrainingDTO> viewTrainingTitleList() {

		return trainingRepository.findAll().stream().map(training -> modelMapper.map(training, PetiteTrainingDTO.class)).collect(Collectors.toList());
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
				originWriter,
				trainingDTO.getTrainingDate(),
				foundEmp,
				trainingDTO.getTrainingDeleteYn());
	}

	@Transactional
	public void insertTraining(com.insadong.application.study.dto.TrainingDTO trainingDTO, long empCode) {

		Employee employee = employeeRepository.findById(empCode).orElseThrow(() -> new IllegalArgumentException("해당 코드로 사원을 조회할 수 없습니다."));
		EmpDTO empDTO = modelMapper.map(employee, EmpDTO.class);
		trainingDTO.setTrainingWriter(empDTO);

		trainingRepository.save(modelMapper.map(trainingDTO, Training.class));
	}

	@Transactional
	public void trainingDelete(Long trainingCode) {

		Training foundTraining = trainingRepository.findById(trainingCode).orElseThrow(() -> new IllegalArgumentException("해당 코드로 과정을 조회할 수 없습니다."));
		trainingRepository.delete(foundTraining);
	}

	public Page<TrainingDTO> selectTrainingListByTrainingTitle(String trainingTitle, int page) {

		Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("trainingCode").descending());
		Page<Training> searchList = trainingRepository.findByTrainingTitleContainsAndTrainingDeleteYn(pageable, trainingTitle, "N");

		return searchList.map(training -> modelMapper.map(training, TrainingDTO.class));
	}

}
