package com.insadong.application.notice.dto;

import com.insadong.application.training.dto.TrainingDTO;

import lombok.Data;

@Data
public class FileDTO {
	
	private Long fileCode;
	
	private Long noticeCode;
	
	private String originFileName;
	
	private String saveFileName;
	
	private String fileFath;
	
	private Long fileSize;
	

}
