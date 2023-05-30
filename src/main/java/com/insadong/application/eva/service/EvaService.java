package com.insadong.application.eva.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insadong.application.common.entity.Eva;
import com.insadong.application.common.entity.Student;
import com.insadong.application.eva.dto.EvaDTO;
import com.insadong.application.eva.repository.EvaRepository;
import com.insadong.application.student.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EvaService {
	
	private final ModelMapper modelMapper;
	private final EvaRepository evaRepository;
	private final StudentRepository studentRepository;
	
	public EvaService(ModelMapper modelMapper, EvaRepository evaRepository
			, StudentRepository studentRepository ) {
		this.modelMapper = modelMapper;
		this.evaRepository = evaRepository;
		this.studentRepository = studentRepository;
	}
	
	/* 관리자 */
	/* 1. 평가 조회 */
	public Page<EvaDTO> selectEvaListByStudentForAdmin(int page, Long stuCode) {
		
		log.info("[EvaService] selectEvaListByStudentForAdmin start ==============================");
	    
	    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.stuCode").descending());
	    
	    Student findStudent = studentRepository.findById(stuCode)
		        .orElseThrow(() -> new IllegalArgumentException("해당 평가가 없습니다. stuCode = " + stuCode));
	    
	    Page<Eva> evaList = evaRepository.findByStudent(pageable, findStudent);
	    Page<EvaDTO> evaDTOList = evaList.map(eva -> modelMapper.map(eva, EvaDTO.class));
	    
	    log.info("[EvaService] evaDTOList.getContent() : {}", evaDTOList.getContent());
	    
	    log.info("[EvaService] selectEvaListByStudentForAdmin end ================================");
	    
	    return evaDTOList;
	}

	/* 관리자 평가 삭제 */
	@Transactional
	public void deleteEva(Long evaCode) {
		 Eva eva = evaRepository.findById(evaCode)
		        .orElseThrow(() -> new IllegalArgumentException("해당 평가가 없습니다. evaCode = " + evaCode));
		 
		 log.info("[EvaService] eva : {} ", eva);
		 System.out.println(evaCode);
		 
		 evaRepository.delete(eva);
		
	}

	/* 사용자 평가 조회 */
	public Page<EvaDTO> selectEvaListByStudent(int page, Long stuCode) {
		log.info("[EvaService] selectEvaListByStudent start ==============================");
	    
	    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.stuCode").descending());
	    
	    Student findStudent = studentRepository.findById(stuCode)
		        .orElseThrow(() -> new IllegalArgumentException("해당 평가가 없습니다. stuCode = " + stuCode));
	    
	    Page<Eva> evaList = evaRepository.findByStudent(pageable, findStudent);
	    Page<EvaDTO> evaDTOList = evaList.map(eva -> modelMapper.map(eva, EvaDTO.class));
	    
	    log.info("[EvaService] evaDTOList.getContent() : {}", evaDTOList.getContent());
	    
	    log.info("[EvaService] selectEvaListByStudent end ================================");
	    
	    return evaDTOList;
	
	}
	
	/* 사용자 평가 등록 */
	public void insertEva(EvaDTO evaDto) {
		log.info("[evaService] insertEva Start ===================================");
		log.info("[evaService] evaDto : {}", evaDto);

		evaRepository.save(modelMapper.map(evaDto, Eva.class));

		log.info("[evaService] insertEva End ==============================");
	}

	/* 사용자 평가 수정 */
	@Transactional
	public void updateEva(EvaDTO evaDto) {
		log.info("[evaService] updateEva start ============================== ");
		log.info("[evaService] evaDto : {}", evaDto);

		Eva originEva = evaRepository.findById(evaDto.getEvaCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 평가가 없습니다. evaCode = " + evaDto.getEvaCode()));

			evaDto.setEvaUpdateTime(new Date());
			
			/* 평가 정보 수정 */
			originEva.update(
					evaDto.getEvaWriteContent(),
					evaDto.getEvaWriteDate(),
					evaDto.getEvaUpdateTime(),
					evaDto.getEvaDeleteStatus()
		
			);

			log.info("[evaService] updateEva end ============================== ");
			
		}
		
	}

	
	
	
	


