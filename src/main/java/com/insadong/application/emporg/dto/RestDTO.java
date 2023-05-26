package com.insadong.application.emporg.dto;

import com.insadong.application.employee.dto.EmployeeDTO;
import lombok.Data;

import java.util.Date;

@Data
public class RestDTO {

    private Long restCode;
    private EmployeeDTO employee;
    private Date restStart;
    private Date restEnd;
    private String restMemo;
    private String restState;
}
