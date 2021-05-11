package com.human.vo;

public class Criteria {

	private int page; //현재 페이지
	private int perPageNum; //한 페이지에 보여줄 게시물 개수
	private int rowStart; //페이지 시작 번호
	private int rowEnd; //페이지 끝 번호
	
	public Criteria() {
		this.page=1;
		this.perPageNum=10;
	}
	
	public void setPage(int page) {
		if (page<=0) {
			this.page=1;
			return;
		}
		this.page=page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum<=0 || perPageNum>100) {
			this.perPageNum=10;
			return;
		}
		this.perPageNum=perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getPageStart() {
		return (this.page-1) * perPageNum;
	}
	
	public int getPerPageNum() {
		return this.perPageNum;
	}
	
	public int getRowStart() {
		rowStart = ((page-1) * perPageNum)+1;
		return rowStart;
	}
	
	public int getRowEnd() {
		rowEnd=rowStart+perPageNum-1;
		return rowEnd;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}
	
	
}
