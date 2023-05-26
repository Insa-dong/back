package com.insadong.application.Tcalendar.entity;

import com.insadong.application.study.entity.EmpEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@ToString
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_CALENDAR")
@SequenceGenerator(name = "CALENDAR_SEQ_GEN", sequenceName = "SEQ_CAL_CODE", allocationSize = 1)
public class Calendar {

	@Id
	@Column(name = "CAL_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CALENDAR_SEQ_GEN")
	private Long calCode;

	@Column(name = "CAL_TITLE")
	private String calTitle;

	@Column(name = "CAL_CONTENT")
	private String calContent;

	@Column(name = "CAL_START_DATE")
	private Date calStartDate;

	@Column(name = "CAL_END_DATE")
	private Date calEndDate;

	@Column(name = "CAL_COLOR")
	private String calColor;

	@ManyToOne
	@JoinColumn(name = "EMP_CODE", updatable = false)
	private EmpEntity employee;

	public void update(String calTitle, Date calStartDate, Date calEndDate) {
		this.calTitle = calTitle;
		this.calStartDate = calStartDate;
		this.calEndDate = calEndDate;
	}
}
