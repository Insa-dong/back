package com.insadong.application.common.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TB_AUTH")
public class Auth {
	
	@Id
	@Column(name="AUTH_CODE")
	private String authCode;
	
	@Column(name="AUTH_NAME")
	private String authName;
	
	@Column(name="AUTH_DISCRIPTION")
	private String authDiscription;
	
}
