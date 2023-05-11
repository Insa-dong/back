package com.insadong.application.student.Service;

import com.insadong.application.common.entity.Student;
import com.insadong.application.student.dto.StudentDTO;
import com.insadong.application.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class StudentService {

	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;

	public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}

	/* 관리자 */

	/* 1. 수강생 전체 조회 */

	public Page<StudentDTO> selectStudentListForAdmin(int page) {

		log.info("[StudentService] selectStudentListForAdmin start ============================== ");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("stuCode").descending());

		Page<Student> studentList = studentRepository.findAll(pageable);
		Page<StudentDTO> studentDtoList = studentList.map(student -> modelMapper.map(student, StudentDTO.class));

		log.info("[StudentService] studentDtoList.getContent() : {}", studentDtoList.getContent());

		log.info("[StudentService] selectStudentListForAdmin end ============================== ");

		return studentDtoList;
	}

	/* 3. 수강생 상세 보기 */
	public StudentDTO selectStudentDetailForAdmin(Long stuCode) {

		log.info("[StudentService] selectStudentForAdmin start ============================== ");
		log.info("[StudentService] stuCode : {}", stuCode);

		Student student = studentRepository.findById(stuCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 수강생이 없습니다. stuCode=" + stuCode));

		StudentDTO studentDto = modelMapper.map(student, StudentDTO.class);

		log.info("[StudentService] studentDto : {}", studentDto);
		log.info("[StudentService] selectStudentForAdmin end ============================== ");

		return studentDto;
	}


	/* 4. 수강생 정보 수정 */
	@Transactional
	public void updateStudent(StudentDTO studentDto) {
		log.info("[StudentService] updateStudent start ============================== ");
		log.info("[StudentService] studentDto : {}", studentDto);

		Student originStudent = studentRepository.findById(studentDto.getStuCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 수강생이 없습니다. stuCode = " + studentDto.getStuCode()));

		/* 수강생 정보 수정 */
		originStudent.update(
				studentDto.getStuName(),
				studentDto.getStuEngName(),
				studentDto.getStuPhone(),
				studentDto.getStuEmail(),
				studentDto.getStuBirth(),
				studentDto.getStuEndSchool()
		);

		log.info("[StudentService] updateStudent end ============================== ");
	}

	/* 5. 수강생 등록 */
	@Transactional
	public void insertStudent(StudentDTO studentDto) {

		log.info("[studentService] insertStudent Start ===================================");
		log.info("[studentService] studentDto : {}", studentDto);

		studentRepository.save(modelMapper.map(studentDto, Student.class));

		log.info("[studentService] insertStudent End ==============================");
	}


}
