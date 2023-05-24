package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@ToString
@Table(name="TB_OFF")
@SequenceGenerator(name="REST_SEQ_GENERATOR", sequenceName="SEQ_REST_CODE", initialValue=1, allocationSize =1)
public class REST {

    @Id
    @Column(name="REST_CODE")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REST_SEQ_GENERATOR")
    private Long restCode;

    @ManyToOne
    @JoinColumn(name = "EMP_CODE")
    private Employee employee;

    @Column(name = "REST_START")
    private Date restStart;

    @Column(name = "REST_OFF")
    private Date restOff;

    @Column(name = "REST_MEMO")
    private String restMemo;
}
