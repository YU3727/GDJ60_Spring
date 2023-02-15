package com.pooh.s1.util;

public class Pager {
//230215 페이징처리를 위해 Mapper에서 사용할 클래스
	
	//객체타입으로 데이터를 받기 위해 멤버변수로 만들어둔다(page)
	//한페이지에 출력할 row의 개수
	private Long perPage;
	//client가 보고싶은 페이지 번호(parameter)
	private Long page;
	
	//table에서 조회할 시작번호
	private Long startRow;
	//table에서 조회할 끝번호
	private Long lastRow;
	
	//전체 row의 개수
//	private Long totalCount;
	
	//jsp에 출력하는 용도
	private Long startNum;
	private Long lastNum;
	
	//시작/마지막 block에서 disabled 시키기 위한 용도
	private boolean before;
	private boolean after;
	
	public Pager() {
		this.perPage=10L;
	}
	
	//startRow, lastRow 계산하는 메서드
	public void makeRow() {
		//this. 는 이 객체(클래스)의~라고 생각
		//null이 올수도 있기 때문에 page, perpage 대신 getter를 쓴다
		this.startRow = (this.getPage()-1)*this.getPerPage()+1;
		this.lastRow = this.getPage()*this.getPerPage();	
	}
	
	//startNum, lastNum을 계산하는 메서드
	public void makeNum(Long totalCount) {
		
		//1. 전체 row의 갯수 구하기
		//2. 총 page의 갯수 구하기
		Long totalPage = totalCount/this.getPerPage();
		//row가 10단위로 떨어지지 않을때 page는 하나씩 더 많아야한다.
		if(totalCount%this.getPerPage() != 0) {
			//totalPage = totalPage + 1;
			//totalPage += 1;
			totalPage++;
		}
		
		if(this.getPage() > totalPage) {
			//현재 page가 totalPage를 넘지 않게 설정
			this.setPage(totalPage);
		}
		
		//3. 한 블럭에 출력할 번호의 개수(page 번호를 몇개씩 나타내줄것인가) -> 5개로 해보자
		Long perBlock = 5L;
		
		//4. 총 블럭의 개수
		Long totalBlock = totalPage/perBlock;
		if(totalPage%perBlock != 0) {
			totalBlock++;
		}
		
		//5. page 번호로 현재 블럭 번호를 구하기
		
		//page 1-5 : cur = 1
		//page 6-10 : cur = 2
		//page 11-15 : cur = 3 ...
		Long curBlock = this.getPage() / perBlock;
		if(this.getPage()%perBlock != 0) {
			curBlock++;
		}
		
		//**현재 블럭이 몇번째 블럭인지 알아야 시작번호, 끝번호를 구할 수 있다
		//6. curBlock의 시작번호, 끝번호를 계산
		/*	curBlock	startNum	lastNum
			1			1			5
			2			6			10
			3			11			15
		*/
		this.startNum = (curBlock-1)*perBlock+1;
		this.lastNum = curBlock*perBlock;
		
		this.after = true;
		if(curBlock == totalBlock) {
			lastNum = totalPage;
			this.after = false;
		}
		
		if(curBlock == 1) {
			this.before = true;
		}
	
	}
	
//	public Long getTotalCount() {
//		return totalCount;
//	}
//
//	public void setTotalCount(Long totalCount) {
//		this.totalCount = totalCount;
//	}
	
	
	public boolean isBefore() {
		return before;
	}
	
	public void setBefore(boolean before) {
		this.before = before;
	}
	
	public boolean isAfter() {
		return after;
	}

	public void setAfter(boolean after) {
		this.after = after;
	}

	public Long getStartNum() {
		return startNum;
	}

	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}

	public Long getLastNum() {
		return lastNum;
	}

	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}

	public Long getPerPage() {
		if(this.perPage == null || this.perPage == 0) {
			this.perPage = 10L;
		}
		return perPage;
	}
	
	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}
	public Long getPage() {
		if(this.page == null || this.page < 1) {
			this.page = 1L;
		}
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
	
}
