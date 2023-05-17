package com.insadong.application.studystu.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Student;
import com.insadong.application.common.entity.Study;
import com.insadong.application.common.entity.StudyStu;
import com.insadong.application.common.entity.StudyStuPK;
import com.insadong.application.common.entity.Training;
import com.insadong.application.student.repository.StudentRepository;
import com.insadong.application.study.dto.StudyStuDTO;
import com.insadong.application.study.repository.StudyRepository;
import com.insadong.application.studystu.repository.StudyStuRepository;
import com.insadong.application.training.repository.TrainingRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudyStuService {

	private final StudyStuRepository studyStuRepository;
	private final ModelMapper modelMapper;
	private final StudentRepository studentRepository;
	private final TrainingRepository trainingRepository;
	private final StudyRepository studyRepository;

	public StudyStuService(StudyStuRepository studyStuRepository,
	                       ModelMapper modelMapper, StudentRepository studentRepository
			, TrainingRepository trainingRepository
			, StudyRepository studyRepository) {
		this.modelMapper = modelMapper;
		this.studyStuRepository = studyStuRepository;
		this.studentRepository = studentRepository;
		this.trainingRepository = trainingRepository;
		this.studyRepository = studyRepository;
	}

	


//	/* 2. 수강생 강의 수정 */
//	@Transactional
//	public void updateStudy(StudyStuDTO studyStuDto) {
//
//	    log.info("[StudyStuService] updateStudy start ============================== ");
//	    log.info("[StudyStuService] studyStuDto : {}", studyStuDto);
//
//	    StudyStu studyStu = studyStuRepository.findById(new StudyStuPK(studyStuDto.getStudyCode(), studyStuDto.getStuCode()))
//	            .orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. studyCode = " + studyStuDto.getStudyCode()));

//	    /* 수강생 정보 수정 */
//	    studyStu.update(
//	            studyStuDto.getStudyEnrollDate(),
//	            studyStuDto.getStudyState()
//	    );
//
//	    log.info("[StudyStuService] updateStudy end ============================== ");

//	}
//
//	/* 3. 수강생 강의 삭제 */
//	@Transactional
//	public void deleteStudy(Long studyCode, Long stuCode) {
//	    StudyStuPK studyStuPK = new StudyStuPK(studyCode, stuCode);
//	    StudyStu studyStu = studyStuRepository.findById(studyStuPK)
//	            .orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. studyCode = " + studyCode));
//
//	    studyStuRepository.delete(studyStu);
//	}

	
	/* 1. 수강생 강의 등록 */
	@Transactional
	public void insertStudy(StudyStuDTO studyStuDto) {

		log.info("[StudyStuService] insertStudy Start ===================================");
		log.info("[StudyStuService] studyStuDto : {}", studyStuDto);

		StudyStu studyStu = new StudyStu();
	    studyStu.setStudyStuPK(new StudyStuPK(studyStuDto.getStudyCode(), studyStuDto.getStudent().getStuCode()));
	    studyStu.setStudyEnrollDate(studyStuDto.getStudyEnrollDate());
	    studyStu.setStudyState(studyStuDto.getStudyState());

	    studyStuRepository.save(studyStu);

		log.info("[StudyStuService] insertStudent End ==============================");

	}


	/* 2. 수강생 강의 수정 */
	@Transactional
	public void updateStudy(StudyStuDTO studyStuDto) {

		log.info("[StudyStuService] updateStudy start ============================== ");
		log.info("[StudyStuService] studyStuDto : {}", studyStuDto);

		StudyStu originStudyStu = studyStuRepository.findById(studyStuDto.getStudyCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. studyCode = " + studyStuDto.getStudyCode()));

		/* 수강생 정보 수정 */
		originStudyStu.update(
			    originStudyStu.getStudyStuPK().getStudyCode(),
			    studyStuDto.getStudyEnrollDate(),
			    studyStuDto.getStudyState()
			);

		log.info("[StudyStuService] updateStudy end ============================== ");

	}
	


	/* 3. 수강생 강의 삭제 */
	@Transactional
	public void deleteStudy(Long studyCode) {
		StudyStu studyStu = studyStuRepository.findById(studyCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. studyCode = " + studyCode));

		studyStuRepository.delete(studyStu);

	}


	/* 4. 수강생 강의 조회 */
	public Page<StudyStuDTO> selectStudyListByStudentForAdmin(int page, Long stuCode) {
	    log.info("[StudyStuService] selectStudyListByStudentForAdmin start ==============================");

	    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.stuCode").descending());

	    Student findStudent = studentRepository.findById(stuCode)
	            .orElseThrow(() -> new IllegalArgumentException("해당 학생이 없습니다. stuCode = " + stuCode));

	    Page<StudyStu> studyStuList = studyStuRepository.findByStudent(pageable, findStudent);
	    Page<StudyStuDTO> studyStuDTOList = studyStuList.map(studyStu -> {
	        StudyStuDTO studyStuDTO = modelMapper.map(studyStu, StudyStuDTO.class);

	        // 강의 정보 가져오기
	        Study study = studyRepository.findById(studyStu.getStudyStuPK().getStudyCode())
	                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. studyCode = " + studyStu.getStudyStuPK().getStudyCode()));

	        // 과정 정보 가져오기
	        Training training = trainingRepository.findById(study.getTraining().getTrainingCode())
	                .orElseThrow(() -> new IllegalArgumentException("해당 과정이 없습니다. trainingCode = " + study.getTraining().getTrainingCode()));

	        studyStuDTO.setTrainingTitle(training.getTrainingTitle());
	        studyStuDTO.setTrainingCount(study.getTraining().getTrainingCount());

	        return studyStuDTO;
	    });

	    log.info("[StudyStuService] studyStuDTOList.getContent() : {}", studyStuDTOList.getContent());
	    log.info("[StudyStuService] selectStudyListByStudentForAdmin end ================================");

	    return studyStuDTOList;
	}

	
}
	
	


