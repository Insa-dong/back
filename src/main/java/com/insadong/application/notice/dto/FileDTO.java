package com.insadong.application.notice.dto;

import com.insadong.application.training.dto.TrainingDTO;

import lombok.Data;

@Data
public class FileDTO {
	
	private Long fileCode;
	
	private String fileDiv;
	
	private NoticeDTO noticeCode;
	
	private TrainingDTO trainingCode;
	
	private String originFileName;
	
	private String saveFileName;
	
	private String fileFath;
	
	private Long fileSize;
	
	private String fileUploadTime;
	

}
