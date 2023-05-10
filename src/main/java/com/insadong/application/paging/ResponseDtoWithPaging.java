package com.insadong.application.paging;

import lombok.Data;

@Data
public class ResponseDtoWithPaging {

	private Object data;
	private PagingButtonInfo pageInfo;

}

