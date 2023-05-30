package com.insadong.application.common.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@DynamicInsert
@Table(name="TB_ADVICE")
@SequenceGenerator(name="ADVICE_SEQ_GENERATOR",
sequenceName="SEQ_ADVICE_CODE",
initialValue=1, allocationSize=1)
public class Advice {
	
	@Id
	@Column(name="ADVICE_LOG_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADVICE_SEQ_GENERATOR")
	private Long adviceLogCode;
	
	@ManyToOne
	@JoinColumn(name="STU_CODE")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private Employee writer;
	
	@Column(name="ADVICE_LOG_CONTENT")
	private String adviceLogContent;
	
	@Column(name="ADVICE_LOG_UPDATE")
	private Date adviceLogUpdate;
	
	@Column(name="ADVICE_LOG_DATE")
	private Date adviceLogDate;
	
	@Column(name="ADVICE_LOG_DELETE")
	private String adviceLogDelete;

	/* Advice entity 수정 용도의 메소드를 별도로 정의 */
	
	public void update(Employee writer, String adviceLogContent, Date adviceLogDate, Date adviceLogUpdate, String adviceLogDelete) {
        this.writer = writer;
        this.adviceLogContent = adviceLogContent;
        this.adviceLogDate = adviceLogDate;
        this.adviceLogUpdate = adviceLogUpdate;
        this.adviceLogDelete = adviceLogDelete;
    }
	
}
		

