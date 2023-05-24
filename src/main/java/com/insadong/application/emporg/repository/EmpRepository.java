package com.insadong.application.emporg.repository;

import com.insadong.application.common.entity.Dept;
import com.insadong.application.common.entity.Employee;
import com.insadong.application.common.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpRepository extends JpaRepository<Employee, Long> {

    /*1. 구성원 전체 조회 - 재직 중*/
    @EntityGraph(attributePaths = {"dept", "job"})
	Page<Employee> findByEmpState(Pageable pageable, String empState);

	/*2. 구성원 부서별 조회*/
	@EntityGraph(attributePaths = {"dept", "job"})
	Page<Employee> findByDeptAndEmpState(Pageable pageable, Dept dept, String empState);

//    /*3-1. 구성원 이름 검색*/
//    @EntityGraph(attributePaths={"dept", "job"})
//    Page<Employee> findByEmpNameContains(Pageable pageable, String empName);
//
//    /*3-2. 구성원 부서 검색*/
//    @EntityGraph(attributePaths={"dept", "job"})
//    Page<Employee> findByDeptDeptCodeIn(Pageable pageable, List<String> findDeptCodeList);
//
//    /*3-3. 구성원 직책 검색*/
//    @EntityGraph(attributePaths={"dept", "job"})
//    Page<Employee> findByJobJobCodeIn(Pageable pageable, List<String> findJobCodeList);

    /* 3-1. 구성원 이름 검색 */
    @EntityGraph(attributePaths = {"dept", "job"})
    Page<Employee> findByEmpNameContainsAndEmpState(Pageable pageable, String empName , String empState);

    /* 3-2. 구성원 부서 검색 */
    @EntityGraph(attributePaths = {"dept", "job"})
    Page<Employee> findByDeptDeptCodeInAndEmpState(Pageable pageable, List<String> findDeptCodeList, String empState);

    /* 3-3. 구성원 직책 검색 */
    @EntityGraph(attributePaths = {"dept", "job"})
    Page<Employee> findByJobJobCodeInAndEmpState(Pageable pageable, List<String> findJobCodeList, String empState);

    Employee findByEmpCode(Long empCode);
    /* 구성원 부서이동*/
    @Query("UPDATE Employee e SET e.dept = :newDept WHERE e.empCode = :empCode")
    void moveEmployeeToDept(@Param("empCode") Long empCode, @Param("newDept") Dept newDept);

    /* 구성원 직책이동*/
    @Query("UPDATE Employee e SET e.dept = :newJob WHERE e.empCode = :empCode")
    void moveEmployeeToJob(@Param("empCode") Long empCode, @Param("newJob") Job newJob);


    /*4. 강사 전체 조회 */
	@EntityGraph(attributePaths = {"dept", "job"})
	List<Employee> findByDeptDeptCode(String de0003);
}
