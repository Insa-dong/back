package com.insadong.application.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
	
	private String grantType;
	private String memberName;
	private String accessToken;
	private Long accessTokenExpiresIn;
	


}
