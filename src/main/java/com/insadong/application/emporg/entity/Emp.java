package com.insadong.application.emporg.entity;

import com.insadong.application.common.entity.Dept;
import com.insadong.application.common.entity.Job;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_EMP")
public class Emp {

    @Id
    @Column(name = "EMP_CODE")
    private Long empCode;

    @ManyToOne
    @JoinColumn(name = "DEPT_CODE")
    private EmpDept dept;

    @ManyToOne
    @JoinColumn(name = "JOB_CODE")
    private EmpJob job;

    @Column(name = "EMP_NAME")
    private String empName;
}
