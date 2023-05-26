package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@ToString
@DynamicInsert
@Table(name="TB_REST")
@SequenceGenerator(name="REST_SEQ_GENERATOR", sequenceName="SEQ_REST_CODE", initialValue=1, allocationSize =1)
public class Rest {

    @Id
    @Column(name="REST_CODE")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REST_SEQ_GENERATOR")
    private Long restCode;

    @ManyToOne
    @JoinColumn(name = "EMP_CODE")
    private Employee employee;

    @Column(name = "REST_START")
    private Date restStart;

    @Column(name = "REST_END")
    private Date restEnd;

    @Column(name = "REST_MEMO")
    private String restMemo;

    @Column(name = "REST_STATE")
    private String restState;
}
