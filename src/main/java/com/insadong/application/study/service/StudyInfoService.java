package com.insadong.application.study.service;

import com.insadong.application.study.dto.PetiteStudyInfoDTO;
import com.insadong.application.study.dto.StudyInfoDTO;
import com.insadong.application.study.entity.StudyInfoEntity;
import com.insadong.application.study.repository.PetiteEmpRepository;
import com.insadong.application.study.repository.PetiteTrainingRepository;
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

@Slf4j
@Service
public class StudyInfoService {
	private final StudyInfoRepository studyInfoRepository;
	private final StudyTimeRepository studyTimeRepository;
	private final PetiteTrainingRepository trainingRepository;
	private final PetiteEmpRepository empRepository;
	private final ModelMapper modelMapper;

	public StudyInfoService(StudyInfoRepository studyInfoRepository, StudyTimeRepository studyTimeRepository, PetiteTrainingRepository trainingRepository, PetiteEmpRepository empRepository, ModelMapper modelMapper) {
		this.studyInfoRepository = studyInfoRepository;
		this.studyTimeRepository = studyTimeRepository;
		this.trainingRepository = trainingRepository;
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
	}

	public Page<StudyInfoDTO> viewStudyInfoList(int page) {

		Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("studyInfoCode").descending());
		Page<StudyInfoEntity> foundStudyInfoList = studyInfoRepository.findByStudyStudyDeleteYn(pageable, "N");

		return foundStudyInfoList.map(studyInfo -> modelMapper.map(studyInfo, StudyInfoDTO.class));
	}

	public PetiteStudyInfoDTO viewPetiteStudyInfo(Long studyInfoCode) {
		return modelMapper.map(studyInfoRepository.findById(studyInfoCode).orElseThrow(() -> new IllegalArgumentException("조회 실패 ~")), PetiteStudyInfoDTO.class);
	}

	@Transactional
	public void modifyStudyInfo(PetiteStudyInfoDTO studyInfo, Long studyInfoCode) {

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

		return foundList.map(studyInfo -> modelMapper.map(studyInfo, StudyInfoDTO.class));
	}

	@Transactional
	public void insertStudy(PetiteStudyInfoDTO studyInfo) {

//		Long trainingCode = studyInfo.getStudy().getTraining().getTrainingCode();
//		TrainingEntity training = trainingRepository.findById(trainingCode).orElseThrow(() -> new IllegalArgumentException("실패 ~"));
//		log.info("training : {} ", training);
//
//
//		TrainingEntity foundEntity = saveEntity.getStudy().getTraining();
//
//		foundEntity.setTrainingCode(training.getTrainingCode());
//		foundEntity.setTrainingTitle(training.getTrainingTitle());
		StudyInfoEntity saveEntity = modelMapper.map(studyInfo, StudyInfoEntity.class);

		studyInfoRepository.save(saveEntity);
	}
}
