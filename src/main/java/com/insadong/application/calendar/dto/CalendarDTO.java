package com.insadong.application.calendar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CalendarDTO {

	private Long calCode;

	private String calTitle;

	private String calContent;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date calStartDate;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date calEndDate;

	private String calColor;

	private PetiteEmpDTO employee;

}
