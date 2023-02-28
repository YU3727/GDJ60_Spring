package com.pooh.s1.board;

public class BoardDTO extends BbsDTO{

	//중간단계 더 필요한 것들이 있는 부모 역할을 하는 DTO(notice, qna, bankbookcomment중 notice qna애만 있는것들)
	//다른 게시판이 더 만들어지면 필요에 따라 중간단계 부모역할의 DTO를 더 만들어 주면 된다.
	private String title;
	private Long hit;
	
	public BoardDTO() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}
	
}
