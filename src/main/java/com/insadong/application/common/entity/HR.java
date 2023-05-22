package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TB_HR")public class HR {

    @Id
    @Column(name = "HR_CODE")
    private Long hrCode;

    @Column(name = "HR_UPDATE_DATE")
    private Date hrUpdateDate;

    @Column(name = "HR_DIV")
    private String hrDiv;

    @ManyToOne
    @JoinColumn(name = "DEPT_CODE")
    private Dept dept;

    @ManyToOne
    @JoinColumn(name = "JOB_CODE")
    private Job job;
}
