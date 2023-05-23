package com.insadong.application.Tcalendar.dto;

import com.insadong.application.study.dto.EmpDTO;
import lombok.Data;

import java.util.Date;

@Data
public class CalendarDTO {

	private Long calCode;

	private String calTitle;

	private String calContent;

	private Date calStartDate;

	private Date calEndDate;

	private EmpDTO employee;
}
