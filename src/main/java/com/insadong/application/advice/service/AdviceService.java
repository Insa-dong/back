package com.insadong.application.advice.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.advice.dto.AdviceDTO;
import com.insadong.application.advice.repository.AdviceRepository;
import com.insadong.application.common.entity.Advice;
import com.insadong.application.common.entity.Student;
import com.insadong.application.student.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdviceService {
	
	private final AdviceRepository adviceRepository;
	private final ModelMapper modelMapper;
	private final StudentRepository studentRepository;
	
	
	public AdviceService(AdviceRepository adviceRepository , ModelMapper modelMapper
			, StudentRepository studentRepository) {
		this.adviceRepository = adviceRepository;
		this.modelMapper = modelMapper;
		this.studentRepository = studentRepository;
	}

	/* 관리자 */
	/* 1. 상담일지 조회 */
	public Page<AdviceDTO> selectAdviceListByStudentForAdmin(int page, Long stuCode) {
	    log.info("[AdviceService] selectAdviceListByStudent start ==============================");
	    
	    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.stuCode").descending());
	    
	    Student findStudent = studentRepository.findById(stuCode)
	        .orElseThrow(() -> new IllegalArgumentException("해당 상담일지가 없습니다. stuCode = " + stuCode));
	    
	    Page<Advice> adviceList = adviceRepository.findByStudent(pageable, findStudent);
	    Page<AdviceDTO> adviceDTOList = adviceList.map(advice -> modelMapper.map(advice, AdviceDTO.class));
	    
	    log.info("[AdviceService] adviceDTOList.getContent() : {}", adviceDTOList.getContent());
	    
	    log.info("[AdviceService] selectAdviceListByStudent end ================================");
	    
	    return adviceDTOList;
	}
	
	/* 2. 상담일지 삭제 */
	@Transactional
	public void deleteAdvice(Long adviceLogCode) {
	    Advice advice = adviceRepository.findById(adviceLogCode)
	            .orElseThrow(() -> new IllegalArgumentException("해당 상담일지가 없습니다. adviceLogCode = " + adviceLogCode));

	    adviceRepository.delete(advice);
	}

	
	/* 사용자 */
	/* 1. 사용자 상담일지 조회 */
	public Page<AdviceDTO> selectAdviceListByStudent(int page, Long stuCode) {
		 log.info("[AdviceService] selectAdviceListByStudent start ==============================");
		    
		  Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.stuCode").descending());
		    
		  Student findStudent = studentRepository.findById(stuCode)
		        .orElseThrow(() -> new IllegalArgumentException("해당 수강생이 없습니다. stuCode = " + stuCode));
		    
		  Page<Advice> adviceList = adviceRepository.findByStudent(pageable, findStudent);
		  Page<AdviceDTO> adviceDTOList = adviceList.map(advice -> modelMapper.map(advice, AdviceDTO.class));
		    
		  log.info("[AdviceService] adviceDTOList.getContent() : {}", adviceDTOList.getContent());
		    
		  log.info("[AdviceService] selectAdviceListByStudent end ================================");
		    
		  return adviceDTOList;
	}

	/* 2. 사용자 상담일지 등록 */
	@Transactional
	public void insertAdvice(AdviceDTO adviceDto) {
		log.info("[adviceService] insertAdvice Start ===================================");
		log.info("[adviceService] adviceDto : {}", adviceDto);

		adviceRepository.save(modelMapper.map(adviceDto, Advice.class));

		log.info("[adviceService] insertAdvice End ==============================");
	}

//	/* 3. 수강생 사용자 상담일지 수정 */
//	@Transactional
//	public void updateAdvice(AdviceDTO adviceDto) {
//		log.info("[StudentService] updateAdvice start ============================== ");
//		log.info("[StudentService] adviceDto : {}", adviceDto);
//		
//		Advice originAdvice = adviceRepository.findById(adviceDto.getAdviceLogCode())
//				.orElseThrow(() -> new IllegalArgumentException("해당 상담일지가 없습니다. adviceLogCode = " + adviceDto.getAdviceLogCode()));
//
//		/* 수강생 정보 수정 */
//		originAdvice.update(
//				adviceDto.getWriter(),
//			    adviceDto.getAdviceLogContent(),
//			    adviceDto.getAdviceLogDate(),
//			    adviceDto.getAdviceLogUpdate(),
//			    adviceDto.getAdviceLogDelete()
//		);
//
//		log.info("[StudentService] updateAdvice end ============================== ");
//		
//	}
	
	/* 3. 수강생 사용자 상담일지 수정 */
	@Transactional
	public void updateAdvice(AdviceDTO adviceDto) {
	    log.info("[StudentService] updateAdvice start ============================== ");
	    log.info("[StudentService] adviceDto : {}", adviceDto);
	    
	    Advice originAdvice = adviceRepository.findById(adviceDto.getAdviceLogCode())
	            .orElseThrow(() -> new IllegalArgumentException("해당 상담일지가 없습니다. adviceLogCode = " + adviceDto.getAdviceLogCode()));

	    /* 수강생 정보 수정 */
	    // 현재 시간으로 adviceLogUpdate 값 설정
	    adviceDto.setAdviceLogUpdate(new Date());
	    
	    originAdvice.update(
	            adviceDto.getWriter(),
	            adviceDto.getAdviceLogContent(),
	            adviceDto.getAdviceLogDate(),
	            adviceDto.getAdviceLogUpdate(),
	            adviceDto.getAdviceLogDelete()
	    );

	    log.info("[StudentService] updateAdvice end ============================== ");
	}


}




