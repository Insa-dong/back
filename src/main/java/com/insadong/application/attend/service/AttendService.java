package com.insadong.application.attend.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.advice.service.AdviceService;
import com.insadong.application.attend.dto.AttendDTO;
import com.insadong.application.attend.repository.AttendRepository;
import com.insadong.application.common.entity.Advice;
import com.insadong.application.common.entity.Attend;
import com.insadong.application.common.entity.Student;
import com.insadong.application.student.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttendService {

	private final ModelMapper modelMapper;
	private final AttendRepository attendRepository;
	private final StudentRepository studentRepository;
	
	public AttendService(ModelMapper modelMapper, AttendRepository attendRepository
			,StudentRepository studentRepository) {
		this.attendRepository = attendRepository;
		this.modelMapper = modelMapper;
		this.studentRepository = studentRepository;
	}
	
	/* 수강생 출결 조회 */
	
	public Page<AttendDTO> selectAttendListByStudent(int page, Long stuCode) {
		
		log.info("[AttendService] selectAttendListByStudent start ==============================");
		
		Student findStu = studentRepository.findById(stuCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 학생이 없습니다. stuCode = " + stuCode));
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.stuCode").descending());
		
		Page<Attend> attendList = attendRepository.findByStudent(pageable, findStu);
	    Page<AttendDTO> attendDTOList = attendList.map(attend -> modelMapper.map(attend, AttendDTO.class));
	    
	    log.info("[AttendService] attendDTOList.getContent() : {}", attendDTOList.getContent());
	    
	    log.info("[AttendService] selectAttendListByStudent end  ==============================");
	    
	    return attendDTOList;
	}
	
	/* 수강생 출결 등록 */
	@Transactional
	public void insertAttend(AttendDTO attendDto) {
		
		attendRepository.save(modelMapper.map(attendDto, Attend.class));
		
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
	public void deleteAttend(Long stuCode) {
		 Attend attend = attendRepository.findById(stuCode)
		            .orElseThrow(() -> new IllegalArgumentException("해당 출결이 없습니다. stuCode = " + stuCode));

		    attendRepository.delete(attend);
	}


}
