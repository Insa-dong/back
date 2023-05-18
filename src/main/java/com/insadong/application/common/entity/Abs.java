package com.insadong.application.common.entity;

import java.time.LocalDate;
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

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="TB_ABS")
@SequenceGenerator(name="ABS_SEQ_GENERATOR", sequenceName="SEQ_ABS_CODE", initialValue=1, allocationSize =1)
public class Abs {

	/* 근태 */
	
	@Id
	@Column(name="ABS_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ABS_SEQ_GENERATOR")
	private Long absCode;			// 근태코드
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Employee empCode;		// 사번
	
	@Column(name="ABS_DATE")
	private LocalDate absDate;			// 출근일자
	
	@Column(name="ABS_START")
	private LocalDateTime absStart;		// 출근시간
	
	@Column(name="ABS_END")
	private LocalDateTime absEnd;			// 퇴근시간
	
	/* 수정 메소드*/
	public void updateAbs(LocalDate absDate, LocalDateTime absStart, LocalDateTime absEnd) {
		
		this.absDate = absDate;
		this.absStart = absStart;
		this.absEnd = absEnd;

	}

}
