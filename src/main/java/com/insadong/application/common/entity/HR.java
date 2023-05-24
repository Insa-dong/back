package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "TB_HR")
@SequenceGenerator(name="HR_SEQ_GENERATOR", sequenceName="SEQ_HR_CODE", initialValue=1, allocationSize =1)
public class HR {

    @Id
    @Column(name = "HR_CODE")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HR_SEQ_GENERATOR")
    private Long hrCode;

    @ManyToOne
    @JoinColumn(name = "EMP_CODE")
    private Employee employee;

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

    public HR(Employee employee, String hrDiv, Dept dept) {
        this.employee = employee;
        this.hrDiv = hrDiv;
        this.dept = dept;
    }

    public HR(Employee employee, String hrDiv, Job job) {
        this.employee = employee;
        this.hrDiv = hrDiv;
        this.job = job;
    }
}
