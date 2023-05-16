package com.insadong.application.study.service;

import com.insadong.application.study.repository.StudyTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudyTimeService {

	private final StudyTimeRepository studyTimeRepository;
	private final ModelMapper modelMapper;

	public StudyTimeService(StudyTimeRepository studyTimeRepository, ModelMapper modelMapper) {
		this.studyTimeRepository = studyTimeRepository;
		this.modelMapper = modelMapper;
	}


//	public List<StudyTimeDTO> findByStudyStudyCode(Long studyCode) {
//		List<StudyTime> foundList = studyTimeRepository.findByStudyStudyCode(studyCode);
//
//		return foundList.stream().map(studyTime -> modelMapper.map(studyTime, StudyTimeDTO.class)).collect(Collectors.toList());
//	}
}
