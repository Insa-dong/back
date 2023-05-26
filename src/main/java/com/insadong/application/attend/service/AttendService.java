package com.insadong.application.attend.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.attend.dto.AttendDTO;
import com.insadong.application.attend.repository.AttendRepository;
import com.insadong.application.common.entity.Attend;
import com.insadong.application.common.entity.Student;
import com.insadong.application.common.entity.Study;
import com.insadong.application.student.repository.StudentRepository;
import com.insadong.application.study.repository.StudyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttendService {

	private final ModelMapper modelMapper;
	private final AttendRepository attendRepository;
	private final StudentRepository studentRepository;
	private final StudyRepository studyRepository;
	
	public AttendService(ModelMapper modelMapper, AttendRepository attendRepository
			,StudentRepository studentRepository, StudyRepository studyRepository) {
		this.attendRepository = attendRepository;
		this.modelMapper = modelMapper;
		this.studentRepository = studentRepository;
		this.studyRepository = studyRepository;
	}
	
	
		/* 수강생 출결 조회 */
		public Page<AttendDTO> selectAttendListByStudent(int page, Long studyCode) {
	    log.info("[AttendService] selectAttendListByStudent start ==============================");

	    Study findStudy = studyRepository.findById(studyCode)
	            .orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. studyCode= " + studyCode));

	    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("attendDate").descending());

	    Page<Attend> attendList = attendRepository.findByStudy(pageable, findStudy);
	    Page<AttendDTO> attendDTOList = attendList.map(attend -> modelMapper.map(attend, AttendDTO.class));

	    log.info("[AttendService] attendDTOList.getContent() : {}", attendDTOList.getContent());

	    log.info("[AttendService] selectAttendListByStudent end  ==============================");

	    return attendDTOList;
	}
		
		/* 수강생 출결 상세 조회 */
		public Page<AttendDTO> selectAttendListDetailByStudent(int page, Long stuCode) {
			log.info("[AttendService] selectAttendListDetailByStudent start ==============================");

		    Student findStudent = studentRepository.findById(stuCode)
		            .orElseThrow(() -> new IllegalArgumentException("해당 학생이 없습니다. stuCode= " + stuCode));

		    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.stuCode").descending());

		    Page<Attend> attendList = attendRepository.findByStudent(pageable, findStudent);
		    Page<AttendDTO> attendDTOList = attendList.map(attend -> modelMapper.map(attend, AttendDTO.class));

		    log.info("[AttendService] attendDTOList.getContent() : {}", attendDTOList.getContent());

		    log.info("[AttendService] selectAttendListDetailByStudent end  ==============================");

		    return attendDTOList;
		}




	/* 수강생 출결 등록 */
	@Transactional
	public void insertAttend(AttendDTO attendDto) {
		log.info("[AttendService] insertAttend Start ===================================");
		log.info("[AttendService] attendDto : {}", attendDto);
		
		attendRepository.save(modelMapper.map(attendDto, Attend.class));
		
		log.info("[AttendService] insertAttend End ==============================");
	}

	/* 수강생 출결 수정 */
	@Transactional
	public void updateAttend(AttendDTO attendDto) {
		log.info("[AttendService] updateAttend start ============================== ");
		log.info("[AttendService] attendDto : {}", attendDto);
		
		Attend originAttend = attendRepository.findById(attendDto.getAttendCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 출결이 없습니다. attendCode = " + attendDto.getAttendCode()));
		
		/* 정보 수정 */
		originAttend.update(
				attendDto.getAttendDate(),
				attendDto.getAttendStatus()
		);
	}

	/* 수강생 출결 삭제 */
	@Transactional
	public void deleteAttend(Long attendCode) {
		 Attend attend = attendRepository.findById(attendCode)
		            .orElseThrow(() -> new IllegalArgumentException("해당 출결이 없습니다. attendCode = " + attendCode));

		    attendRepository.delete(attend);
	}

	
	/* 수강생 날짜별 출석 검색*/
	public Page<AttendDTO> selectStudentAttends(LocalDate attendDate, int page) {
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("attendDate").descending());

		Page<Attend> attendList = attendRepository.findByAttendDate(attendDate, pageable);

		Page<AttendDTO> attendDtoList = attendList.map(attend -> modelMapper.map(attend, AttendDTO.class));
	
		return attendDtoList;
	}
	

}

