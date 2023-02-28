package com.pooh.s1.board;

import java.sql.Date;

public class BbsDTO {

	//전체 게시판의 공통요소를 모은 DTO - 최상위 부모역할을 할 것
	//primitive type보다는 reference type으로 - Null 처리 때문에
	private Long num;
	private String contents;
	private String writer;
	private Date regDate; //Date는 java.sql의 date 사용
	
	public BbsDTO() {
		
	}
	
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
