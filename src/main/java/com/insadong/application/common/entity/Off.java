package com.insadong.application.common.entity;

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

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="TB_OFF")
@SequenceGenerator(name="OFF_SEQ_GENERATOR", sequenceName="SEQ_OFF_CODE", initialValue=1, allocationSize =1)
public class Off {
	
  /* 연차 */
	
	@Id
	@Column(name="")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OFF_SEQ_GENERATOR")
	private Long signCode;					// 신청코드
	
	@Column(name="OFF_START")
	private Date offStart;					// 시작일
	
	@Column(name="OFF_END")
	private Date offEnd;					// 종료일
	
	@Column(name="OFF_DAY")
	private Long offDay;					// 일수
	
	@Column(name="SIGN_REASON")
	private String signReason;				// 신청 사유
	
	@Column(name="SIGN_STATUS")
	private String signStatus;				// 승인 상태
	
	@ManyToOne
	@JoinColumn(name="SIGN_REQUESTER")
	private Employee signRequester;			// 신청자 emp_code 조인
	
	@Column(name="OFF_DIV")
	private String offDiv;					// 종류
	
	@ManyToOne
	@JoinColumn(name="SIGN_PAYER")
	private Employee signPayer;				// 결재자 emp_code 조인
	
	@Column(name="REQUEST_DATE")
	private Date requestDate;				// 신청일
	
	@Column(name="RETURN_REASON")
	private String returnReason;			// 반려 사유
	
	@Column(name="HANDLE_DATE")
	private Date handleDate;				// 처리 일자
	
}

/*
SIGN_CODE	NUMBER
OFF_START	DATE
OFF_END	DATE
OFF_DAY	NUMBER
SIGN_REASON	VARCHAR2(1000 BYTE)
SIGN_STATUS	VARCHAR2(100 BYTE)
SIGN_REQUESTER	NUMBER
OFF_DIV	VARCHAR2(100 BYTE)
SIGN_PAYER	NUMBER
REQUEST_DATE	DATE
RETURN_REASON	VARCHAR2(1000 BYTE)
HANDLE_DATE	DATE  

*/