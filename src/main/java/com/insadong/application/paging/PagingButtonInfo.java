package com.insadong.application.paging;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PagingButtonInfo {

	private int currentPage;
	private int startPage;
	private int endPage;
	private int maxPage;


}

