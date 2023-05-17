package com.insadong.application.notice.dto;

import java.util.Date;

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

}
