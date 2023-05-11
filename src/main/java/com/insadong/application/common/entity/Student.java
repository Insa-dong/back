package com.insadong.application.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="TB_STU")
@SequenceGenerator(name="STU_SEQ_GENERATOR",
sequenceName="SEQ_STU_CODE",
initialValue=1, allocationSize=1)
public class Student {

	@Id
	@Column(name="STU_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STU_SEQ_GENERATOR")
	private Long stuCode;
	
	@Column(name="STU_NAME")
	private String stuName;
	
	@Column(name="STU_ENG_NAME")
	private String stuEngName;
	
	@Column(name="STU_PHONE")
	private String stuPhone;
	
	@Column(name="STU_EMAIL")
	private String stuEmail;
	
	@Column(name="STU_BIRTH")
	private Date stuBirth;
	
	@Column(name="STU_END_SCHOOL")
	private String stuEndSchool;
	
	/* Student entity 수정 용도의 메소드를 별도로 정의 */
	public void update(String stuName, String stuEngName, String stuPhone, 
			String stuEmail, Date stuBirth, String stuEndSchool) {
		
		this.stuName = stuName;
		this.stuEngName = stuEngName;
		this.stuPhone = stuPhone;
		this.stuEmail = stuEmail;
		this.stuBirth = stuBirth;
		this.stuEndSchool = stuEndSchool;	
		
	}
	
}
