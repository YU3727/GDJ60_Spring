package com.pooh.s1.util;

public class Pager {
//230215 페이징처리를 위해 Mapper에서 사용할 클래스
	
	//mapper에서의 사용을 위해 추가(pager 상속받고 다른 pager 만들어도 됨)
	private Long bookNumber;
	private Long Num;
	
	//mapper에 data type은 하나만 받을 수 있기 때문에 여기에 검색 기능을 담당하는 것들도 추가한다
	private String kind;
	private String search;
	
	//객체타입(pager)으로 데이터를 받기 위해 멤버변수로 만들어둔다(page)
	//한페이지에 출력할 row의 개수
	private Long perPage;
	
	//한블럭당 출력할 번호의 개수
	private Long perBlock;
	
	private Long totalPage;
	
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
		//getter를 쓰는 이유는 그냥 멤버변수를 쓰면 null이나 이상한값이 들어가있을 수 있다. getter에 그런걸 방지하는 작업을 해뒀기 떄문에
		//멤버변수로 쓰지않고 getter를 쓴다
		
		//1. 전체 row의 갯수 구하기
		//2. 총 page의 갯수 구하기
		this.totalPage = totalCount/this.getPerPage();
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
		
		
		//4. 총 블럭의 개수
		Long totalBlock = totalPage/getPerBlock();
		if(totalPage%perBlock != 0) {
			totalBlock++;
		}
		
		//5. page 번호로 현재 블럭 번호를 구하기
		
		//page 1-5 : cur = 1
		//page 6-10 : cur = 2
		//page 11-15 : cur = 3 ...
		Long curBlock = this.getPage() / getPerBlock();
		if(this.getPage()%getPerBlock() != 0) {
			curBlock++;
		}
		
		//**현재 블럭이 몇번째 블럭인지 알아야 시작번호, 끝번호를 구할 수 있다
		//6. curBlock의 시작번호, 끝번호를 계산
		/*	curBlock	startNum	lastNum
			1			1			5
			2			6			10
			3			11			15
		*/
		this.startNum = (curBlock-1)*getPerBlock()+1;
		this.lastNum = curBlock*getPerBlock();
		
		//7.
		this.after = true;
		if(curBlock == totalBlock) {
			lastNum = totalPage;
			this.after = false;
		}
		
		if(curBlock == 1) {
			this.before = true;
		}
		
		//8.
		if(curBlock == totalBlock) {
			this.lastNum = totalPage;
		}
	
	}
	
//	public Long getTotalCount() {
//		return totalCount;
//	}
//
//	public void setTotalCount(Long totalCount) {
//		this.totalCount = totalCount;
//	}
	
	
	public Long getNum() {
		return Num;
	}
	
	public void setNum(Long num) {
		Num = num;
	}
	
	public Long getBookNumber() {
		return bookNumber;
	}
	
	public void setBookNumber(Long bookNumber) {
		this.bookNumber = bookNumber;
	}
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSearch() {
		//search에 아무것도 안들어오면 전체를 검색
		if(search == null) {
			search = "";
		}
		return search; //"%"+search+"%";
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public Long getTotalPage() {
		//얘는 외부에서 값을 넣어줄 필요가 없기 떄문에 가져다 쓸 수 있게 getter만 만든다
		return totalPage;
	}
	
	public Long getPerBlock() {
		if(this.perBlock == null || this.perBlock < 1) {
			this.perBlock = 5L;
		}
		return perBlock;
	}

	public void setPerBlock(Long perBlock) {
		this.perBlock = perBlock;
	}

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
