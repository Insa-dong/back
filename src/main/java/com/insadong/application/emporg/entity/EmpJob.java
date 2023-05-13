package com.insadong.application.emporg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_JOB")
public class EmpJob {

    @Id
    @Column(name = "JOB_CODE")
    private String jobCode;

    @Column(name = "JOB_NAME")
    private String jobName;

}
