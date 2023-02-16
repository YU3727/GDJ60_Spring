package com.pooh.s1.util;

public class Pagination {
//230216 pager 스스로 만들기
	
	
	//table에서 조회할 시작과 끝번호
	private Long startRow;
	private Long lastRow;
	
	//전체 row 개수
	private Long totalCount;
	
	//client가 보고싶은 페이지 번호(parameter로 씀), 페이지당 볼 row의 개수
	private Long page;
	private Long perPage;
	
	
	public Pagination() {
		
	}
	
	//startRow, lastRow 계산 메서드
	public void makeRow() {
		this.startRow = (page-1)*perPage +1;
		this.lastRow = page*perPage;
	}
	

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getStartRow() {
		return startRow;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public Long getLastRow() {
		return lastRow;
	}

	public void setLastRow(Long lastRow) {
		this.lastRow = lastRow;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
