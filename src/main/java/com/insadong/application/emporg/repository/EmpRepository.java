package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Dept;
import com.insadong.application.common.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpRepository extends JpaRepository<Employee, Long> {

    /*1. 구성원 전체 조회 - 재직 중*/
    @EntityGraph(attributePaths = {"dept", "job"})
    Page<Employee> findByEmpStateIn(Pageable pageable, List<String> empStates);

	/*2. 구성원 부서별 조회*/
    @EntityGraph(attributePaths = {"dept", "job"})
    Page<Employee> findByDeptAndEmpStateIn(Pageable pageable, Dept dept, List<String> empStates);


    /* 3-1. 구성원 이름 검색 */
    @EntityGraph(attributePaths = {"dept", "job"})
    Page<Employee> findByEmpNameContainsAndEmpStateIn(Pageable pageable, String empName, List<String> empStates);

    /* 3-2. 구성원 부서 검색 */
    @EntityGraph(attributePaths = {"dept", "job"})
    Page<Employee> findByDeptDeptCodeInAndEmpStateIn(Pageable pageable, List<String> findDeptCodeList, List<String> empStates);


    /* 3-3. 구성원 직책 검색 */
    @EntityGraph(attributePaths = {"dept", "job"})
    Page<Employee> findByJobJobCodeInAndEmpStateIn(Pageable pageable, List<String> findJobCodeList, List<String> empStates);


    Employee findByEmpCode(Long empCode);

    /* 휴직 검색*/
    @Query("SELECT e.empCode FROM Employee e WHERE e.empName LIKE %:empName%")
    List<Long> findByEmpNameContains(String empName);


    /*4. 강사 전체 조회 */
	@EntityGraph(attributePaths = {"dept", "job"})
	List<Employee> findByDeptDeptCode(String de0003);
}
