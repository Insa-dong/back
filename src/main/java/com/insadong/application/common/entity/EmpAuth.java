package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "TB_EMP_AUTH")
public class EmpAuth implements Serializable {

	@EmbeddedId
	private EmpAuthPK empAuthPK;

	@ManyToOne
	@JoinColumn(name = "AUTH_CODE", insertable = false, updatable = false)
	private Auth auth;
}
