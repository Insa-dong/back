package com.insadong.application.emporg.service;

import com.insadong.application.common.entity.*;
import com.insadong.application.employee.dto.EmployeeDTO;
import com.insadong.application.employee.repository.EmployeeRepository;
import com.insadong.application.emporg.dto.EmpHRDTO;
import com.insadong.application.emporg.dto.RestDTO;
import com.insadong.application.emporg.repository.*;
import com.insadong.application.study.repository.StudyInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmpService {

	private final EmpRepository empRepository;
	private final ModelMapper modelMapper;
	private final EmpDeptRepository empDeptRepository;
	private final EmpJobRepository empJobRepository;
	private final EmployeeRepository employeeRepository;
	private final StudyInfoRepository studyInfoRepository;

	private final EmpHRRepository empHRRepository;

	private final RestRepository restRepository;


	public EmpService(EmpRepository empRepository, ModelMapper modelMapper, EmpDeptRepository empDeptRepository, EmpJobRepository empJobRepository, EmployeeRepository employeeRepository
			, StudyInfoRepository studyInfoRepository, EmpHRRepository empHRRepository, RestRepository restRepository) {
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
		this.empDeptRepository = empDeptRepository;
		this.empJobRepository = empJobRepository;
		this.employeeRepository = employeeRepository;
		this.studyInfoRepository = studyInfoRepository;
		this.empHRRepository = empHRRepository;
		this.restRepository = restRepository;
	}

	/*1. 구성원 전체 조회*/
	public Page<EmployeeDTO> selectEmpList(int page, List<String> empStates) {
		log.info("[EmpService] selectEmpList start ==============================");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("empCode").descending());

		Page<Employee> empList = empRepository.findByEmpStateIn(pageable, empStates);
		Page<EmployeeDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmployeeDTO.class));

		log.info("[EmpService] selectEmpList.getContent(): {}", empDTOList.getContent());

		log.info("[EmpService] selectEmpList end ===============================");

		return empDTOList;
	}


	/*2. 구성원 부서별 조회*/
	public Page<EmployeeDTO> selectEmpListByDept(int page, String deptCode) {
		log.info("[EmpService] selectEmpListByDept start ==============================");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("empCode").descending());

		/*dept 엔티티 조회*/
		Dept findDept = empDeptRepository.findById(deptCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 부서가 없습니다. deptCode =" + deptCode));

		List<String> empStates = Arrays.asList("재직중", "휴직중");
		Page<Employee> empList = empRepository.findByDeptAndEmpStateIn(pageable, findDept, empStates);
		Page<EmployeeDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmployeeDTO.class));

		log.info("[EmpService] selectEmpListByDept.getContent(): {}", empDTOList.getContent());

		log.info("[EmpService] selectEmpListByDept end ===============================");

		return empDTOList;
	}


	/*3. 구성원 검색*/
	public Page<EmployeeDTO> searchEmpByNameAndDeptAndJob(int page, String searchOption, String searchKeyword) {
		log.info("[EmpService] searchEmpByNameAndDeptAndJob start ==============================");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("empCode").descending());
		List<String> empStates = Arrays.asList("재직중", "휴직중");

		if (searchOption.equals("name")) {
			Page<Employee> empList = empRepository.findByEmpNameContainsAndEmpStateIn(pageable, searchKeyword, empStates);
			Page<EmployeeDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmployeeDTO.class));
			log.info("[EmpService] searchEmpByNameAndDeptAndJob.getContent(): {}", empDTOList.getContent());
			return empDTOList;
		} else if (searchOption.equals("dept")) {
			List<String> findDeptCodeList = empDeptRepository.findByDeptNameContains(searchKeyword);
			Page<Employee> empList = empRepository.findByDeptDeptCodeInAndEmpStateIn(pageable, findDeptCodeList, empStates);
			Page<EmployeeDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmployeeDTO.class));
			log.info("[EmpService] searchEmpByNameAndDeptAndJob.getContent(): {}", empDTOList.getContent());
			return empDTOList;
		} else if (searchOption.equals("job")) {
			List<String> findJobCodeList = empJobRepository.findByJobNameContains(searchKeyword);
			Page<Employee> empList = empRepository.findByJobJobCodeInAndEmpStateIn(pageable, findJobCodeList, empStates);
			Page<EmployeeDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmployeeDTO.class));
			log.info("[EmpService] searchEmpByNameAndDeptAndJob.getContent(): {}", empDTOList.getContent());
			return empDTOList;
		} else {
			throw new IllegalArgumentException("유효하지 않은 검색 옵션입니다.");
		}
	}



	/*4. 부서, 직책 조회*/
	public Map<String, Object> selectEmpDeptJobList() {
		List<Dept> deptList = empDeptRepository.findAll();
		List<Job> jobList = empJobRepository.findAll();

		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put("deptList", deptList);
		resultMap.put("jobList", jobList);

		return resultMap;
	}

	/* 5. 구성원 등록 */
	@Transactional
	public void insertEmp(EmployeeDTO employeeDTO) {
		log.info("[EmpService] insertEmp : {}", employeeDTO);
		empRepository.save(modelMapper.map(employeeDTO, Employee.class));
	}

	/* 6. 구성원 상세 조회 */
	public EmployeeDTO selectEmpDetail(Long empCode) {

		Employee employee = employeeRepository.findById(empCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 구성원이 없습니다. empCode=" + empCode));

		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

		return employeeDTO;

	}

	/* 7. 인사이력 조회*/
	public Page<EmpHRDTO> selectEmpRecord(int page, Long empCode) {

		Employee employee = empRepository.findByEmpCode(empCode);

		log.info("service start ========== ");
		Pageable pageable = PageRequest.of(page - 1, 4, Sort.by("hrCode").descending());
		Page<HR> empHRList = empHRRepository.findByEmployee(pageable, employee);
		log.info("service end=============");
		return empHRList.map(hr -> modelMapper.map(hr, EmpHRDTO.class));
	}

	/* 8. 구성원 부서이동*/
	@Transactional
	public void updateEmpDept(EmployeeDTO employeeDTO){
		log.info("[EmpService] updateEmpDept start ============================== ");
		log.info("[EmpService] employeeDTO : {}", employeeDTO);

		Employee originEmployee = empRepository.findById(employeeDTO.getEmpCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 구성원이 없습니다. empCode = " + employeeDTO.getEmpCode()));

		empHRRepository.save(new HR(originEmployee, "부서이동", originEmployee.getDept()));

		originEmployee.updateDept(
				modelMapper.map(employeeDTO.getDept(), Dept.class)
		);

		log.info("[EmpService] updateEmpDept end ============================== ");
	}

	/* 8. 구성원 직책변동*/
	@Transactional
	public void updateEmpJob(EmployeeDTO employeeDTO){
		log.info("[EmpService] updateEmpJob start ============================== ");
		log.info("[EmpService] employeeDTO : {}", employeeDTO);

		Employee originEmployee = empRepository.findById(employeeDTO.getEmpCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 구성원이 없습니다. empCode = " + employeeDTO.getEmpCode()));

		empHRRepository.save(new HR(originEmployee, "직책변경", originEmployee.getJob()));

		originEmployee.updateJob(
				modelMapper.map(employeeDTO.getJob(), Job.class)
		);

		log.info("[EmpService] updateEmpJob end ============================== ");
	}

	/*9. 구성원 삭제*/
	@Transactional
	public void deleteEmp(Long empCode) {
		Employee originEmp = empRepository.findById(empCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 구성원이 없습니다."));

		originEmp.setEmpState("퇴사");
		originEmp.setEndDate(new Date());
	}

	/* 12. 휴직 상태 변경*/
	@Transactional
	public void updateRestState(RestDTO restDTO) {
		Rest originRest = restRepository.findById(restDTO.getRestCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 휴직내역이 없습니다. restCode = " + restDTO.getRestCode()));


		if ("승인".equals(restDTO.getRestState())) {
			originRest.setRestState("승인");
		} else if ("반려".equals(restDTO.getRestState())) {
			originRest.setRestState("반려");
		}

	}


	/* 10. 휴직 신청*/
	@Transactional
	public void insertEmpRest(RestDTO restDTO) {
		restRepository.save(modelMapper.map(restDTO, Rest.class));
	}




	/* 11. 휴직 리스트 조회*/
	public Page<RestDTO> selectRestList(int page){
		log.info("[EmpService] selectRestList start ==============================");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("restCode").descending());

		Page<Rest> empList = restRepository.findAll(pageable);
		Page<RestDTO> RestDTOList = empList.map(rest -> modelMapper.map(rest, RestDTO.class));

		log.info("[EmpService] selectRestList.getContent(): {}", RestDTOList.getContent());

		log.info("[EmpService] selectRestList end ===============================");

		return RestDTOList;
	}









	public List<com.insadong.application.study.dto.EmpDTO> viewTeacherList() {

		return empRepository.findByDeptDeptCode("DE0003").stream().map(teacher -> modelMapper.map(teacher, com.insadong.application.study.dto.EmpDTO.class)).collect(Collectors.toList());
	}
}
