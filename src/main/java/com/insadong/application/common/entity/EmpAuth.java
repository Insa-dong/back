package com.insadong.application.common.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EmpAuth implements Serializable {
	
	@EmbeddedId
	private EmpAuthPK empAuthPK;
	
	@ManyToOne
	@JoinColumn(name="AUTH_CODE", insertable=false, updatable=false)
	private Auth auth;
}
