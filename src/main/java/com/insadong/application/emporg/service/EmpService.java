package com.insadong.application.emporg.service;

import com.insadong.application.common.entity.Dept;
import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Job;
import com.insadong.application.emporg.dto.EmpDTO;
import com.insadong.application.emporg.dto.EmpDeptDTO;
import com.insadong.application.emporg.dto.EmpJobDTO;
import com.insadong.application.emporg.repository.EmpDeptRepository;
import com.insadong.application.emporg.repository.EmpJobRepository;
import com.insadong.application.emporg.repository.EmpRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Slf4j
@Service
public class EmpService {

	private final EmpRepository empRepository;
	private final ModelMapper modelMapper;
	private final EmpDeptRepository empDeptRepository;
	private final EmpJobRepository empJobRepository;

	public EmpService(EmpRepository empRepository, ModelMapper modelMapper, EmpDeptRepository empDeptRepository, EmpJobRepository empJobRepository) {
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
		this.empDeptRepository = empDeptRepository;
		this.empJobRepository = empJobRepository;
	}

	/*1. 구성원 전체 조회*/
	public Page<EmpDTO> selectEmpList(int page) {

		log.info("[EmpService] selectEmpList start ============================== ");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("empCode").descending());

		Page<Employee> empList = empRepository.findAll(pageable);
		Page<EmpDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmpDTO.class));

		log.info("[EmpService] selectEmpList.getContent() : {}", empDTOList.getContent());

		log.info("[EmpService] selectEmpList end ============================== ");

		return empDTOList;
	}

	/*2. 구성원 부서별 조회*/
	public Page<EmpDTO> selectEmpListByDept(int page, String deptCode) {

		log.info("[EmpService] selectEmpListByDept start ============================== ");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("empCode").descending());

		/*dept 엔티티 조회*/
		Dept findDept = empDeptRepository.findById(deptCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 부서가 없습니다. deptCode =" + deptCode));


		Page<Employee> empList = empRepository.findByDept(pageable, findDept);
		Page<EmpDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmpDTO.class));

		log.info("[EmpService] selectEmpListByDept.getContent() : {}", empDTOList.getContent());

		log.info("[EmpService] selectEmpListByDept end ============================== ");

		return empDTOList;
	}

    /*3. 구성원 검색*/
    public Page<EmpDTO> searchEmpByNameAndDeptAndJob(int page, String searchOption, String searchKeyword){

        log.info("[EmpService] searchEmpByNameAndDeptAndJob start ==============================");

        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("empCode").descending());

        if (searchOption.equals("name")) {
            Page<Employee> empList = empRepository.findByEmpNameContains(pageable, searchKeyword);
            Page<EmpDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmpDTO.class));

            log.info("[EmpService] searchEmpByNameAndDeptAndJob.getContent() : {}", empDTOList.getContent());

            return empDTOList;

        } else if (searchOption.equals("dept")) {
            List<String> findDeptCodeList = empDeptRepository.findByDeptNameContains(searchKeyword);

            Page<Employee> empList = empRepository.findByDeptDeptCodeIn(pageable, findDeptCodeList);
            Page<EmpDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmpDTO.class));

            log.info("[EmpService] searchEmpByNameAndDeptAndJob.getContent() : {}", empDTOList.getContent());

            return empDTOList;

        } else if (searchOption.equals("job")) {
            List<String> findJobCodeList = empJobRepository.findByJobNameContains(searchKeyword);

            Page<Employee> empList = empRepository.findByJobJobCodeIn(pageable, findJobCodeList);
            Page<EmpDTO> empDTOList = empList.map(emp -> modelMapper.map(emp, EmpDTO.class));

            log.info("[EmpService] searchEmpByNameAndDeptAndJob.getContent() : {}", empDTOList.getContent());

            return empDTOList;
        } else {
            throw new IllegalArgumentException("유효하지 않은 검색 옵션입니다.");
        }


	}

	/*4. 부서 조회*/
	public List<EmpDeptDTO> selectEmpDeptList(){
		List<Dept> deptList = empDeptRepository.findAll();
		return deptList.stream().map(dept -> modelMapper.map(dept, EmpDeptDTO.class)).collect(Collectors.toList());
	}

	/*5. 직책 조회*/
	public List<EmpJobDTO> selectEmpJobList(){
		List<Job> jobList = empJobRepository.findAll();
		return jobList.stream().map(job -> modelMapper.map(job, EmpJobDTO.class)).collect(Collectors.toList());
	}

	public List<com.insadong.application.study.dto.EmpDTO> viewTeacherList() {
        
		return empRepository.findByDeptDeptCode("DE0003").stream().map(teacher -> modelMapper.map(teacher, com.insadong.application.study.dto.EmpDTO.class)).collect(Collectors.toList());
	}
}
