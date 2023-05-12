package com.insadong.application.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class EmpAuthPK implements Serializable {
	
	@Column(name="AUTH_CODE")
	private String authCode;
	@Column(name="EMP_CODE")
	private Long empCode;

}
