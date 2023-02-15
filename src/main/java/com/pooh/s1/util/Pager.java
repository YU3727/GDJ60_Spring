package com.pooh.s1.util;

public class Pager {
//230215 페이징처리를 위해 Mapper에서 사용할 클래스
	
	private Long startRow;
	private Long lastRow;
	
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
	
}
