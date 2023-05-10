package com.insadong.application.paging;

import org.springframework.data.domain.Page;

public class Pagenation {

	public static PagingButtonInfo getPagingButtonInfo(Page page) {

//		현재 페이지
		int currentPage = page.getNumber() + 1;
//		버튼 카운트 설정
		int defaultButtonCount = 5;
//		시작 페이지
		int startPage;
//		마지막 페이지
		int endPage;

		startPage = (int) (Math.ceil((double) currentPage / defaultButtonCount) - 1) * defaultButtonCount + 1;
		endPage = startPage + defaultButtonCount - 1;

		if (page.getTotalPages() < endPage)
			endPage = page.getTotalPages();

		if (page.getTotalPages() == 0 && endPage == 0)
			endPage = startPage;
//      현재 페이지, 시작 페이지, 마지막 페이지, 총 페이지 갯수를 반환.
		return new PagingButtonInfo(currentPage, startPage, endPage, page.getTotalPages());
	}

}

