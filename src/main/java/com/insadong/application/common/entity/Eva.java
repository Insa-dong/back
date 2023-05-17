package com.insadong.application.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "TB_EVA")
@SequenceGenerator(name = "EVA_SEQ_GENERATOR",
		sequenceName = "SEQ_EVA_CODE",
		initialValue = 1, allocationSize = 1)
public class Eva {

	@Id
	@Column(name = "EVA_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVA_SEQ_GENERATOR")
	private Long evaCode;

	@ManyToOne
	@JoinColumn(name = "STUDY_INFO_CODE")
	private StudyInfo StudyInfo;

	@ManyToOne
	@JoinColumn(name = "STU_CODE")
	private Student student;

	@Column(name = "EVA_WRITE_CONTENT")
	private String evaWriteContent;

	@Column(name = "EVA_WRITE_DATE")
	private Date evaWriteDate;

	@Column(name = "EVA_UPDATE_TIME")
	private String evaUpdateTime;

	@Column(name = "EVA_DELETE_STATUS")
	private String evaDeleteStatus;

	/* Eva Entity 수정 용도의 메소드를 별도의 정의 */
	public void update(String evaWriteContent, Date evaWriteDate,
	                   String evaUpdateTime, String evaDeleteStatus) {

		this.evaWriteContent = evaWriteContent;
		this.evaWriteDate = evaWriteDate;
		this.evaUpdateTime = evaUpdateTime;
		this.evaDeleteStatus = evaDeleteStatus;

	}

}
