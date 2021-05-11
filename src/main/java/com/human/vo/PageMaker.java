package com.human.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int currentPageNum; //현재 페이지
	private int totalCount; //총 게시물 개수
	private int startPage; //현재 화면의 시작 페이지 넘버
	private int endPage; //현재 화면의 마지막 페이지 넘버
	private boolean prev;
	private boolean next;
	private int displayPageNum =10; //한번에 보여줄 페이지 개수
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public Criteria getCri() {
		return cri;
	}
	
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	
	private void calcData() {
		currentPageNum=cri.getPage();
		endPage=(int) (Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		startPage=(endPage-displayPageNum)+1;
		
		//제일 마지막 페이지 = tempEndPage
		int tempEndPage=(int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		if(endPage>tempEndPage) {
			endPage=tempEndPage;
		}
		
		prev = startPage==1 ? false : true;
		next = endPage*cri.getPerPageNum() >= totalCount ? false : true;
		
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
									.queryParam("page",page)
									.queryParam("perPageNum", cri.getPerPageNum())
									.build();
		
		return uriComponents.toUriString();
	}
	
	public String makeSearch(int page) {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType",((SearchCriteria)cri).getSearchType())
				.queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
				.build();
		return uriComponents.toUriString();
	}

	private Object encoding(String keyword) {
		if(keyword==null || keyword.trim().length()==0) {
			return "";
		}
		
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}
		
	}
}
