package com.insadong.application.paging;

import lombok.Data;

@Data
public class ResponseDTOWithPaging {

	private Object data;
	private PagingButtonInfo pageInfo;

}

