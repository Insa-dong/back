package com.insadong.application.emporg.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.employee.dto.EmployeeDTO;
import lombok.Data;

import java.util.Date;
@Data
public class EmpHRDTO {

    private Long hrCode;
    private EmployeeDTO employeeDTO;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hrUpdateDate;
    private String hrDiv;
    private EmpDeptDTO dept;
    private EmpJobDTO job;
}
