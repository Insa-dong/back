package com.insadong.application.notice.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insadong.application.employee.dto.EmployeeDTO;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private Long noticeCode;

	private String noticeTitle;
	
	private String noticeContent;
	
	private Date noticeWriteDate;
	
	private EmployeeDTO noticeWriter;
	
	private Date noticeModifyDate;
	
	@JsonIgnore
	private List<MultipartFile> noticeFile;
	
	private List<FileDTO> fileList;
	
	
	

}
