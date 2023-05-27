package com.insadong.application.emporg.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insadong.application.employee.dto.EmployeeDTO;
import lombok.Data;

import java.util.Date;

@Data
public class RestDTO {

    private Long restCode;
    private EmployeeDTO employee;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date restStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date restEnd;
    private String restMemo;
    private String restState;
}
