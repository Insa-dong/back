package com.insadong.application.emporg.dto;

import com.insadong.application.employee.dto.EmployeeDTO;
import lombok.Data;

import java.util.Date;
@Data
public class EmpHRDTO {

    private Long hrCode;
    private EmployeeDTO employeeDTO;
    private Date hrUpdateDate;
    private String hrDiv;
    private EmpDeptDTO dept;
    private EmpJobDTO job;
}
