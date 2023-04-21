package com.yedam.common;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PageDTO {

	private int startPage;	//
	private int endPage;	//
	private boolean prev;	//이전페이지있는지
	private boolean next;	//다음페이지있는지
	private int pageNum;	//현재페이지
	
	public PageDTO(int pageNum, int total) {
		this.pageNum = pageNum;
		
		this.endPage = (int) Math.ceil(this.pageNum/10.0)*10;	//ceil: 올림
		this.startPage = this.endPage - 9;
		
		int realEnd = (int) (Math.ceil(total / 10.0));		//실제 마지막 페이지
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;		
	}
}
