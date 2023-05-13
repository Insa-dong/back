package com.insadong.application.emporg.dto;


import lombok.Data;

@Data
public class EmpDTO {

    private Long empCode;
    private EmpDeptDTO dept;
    private EmpJobDTO job;
    private String empName;

}
