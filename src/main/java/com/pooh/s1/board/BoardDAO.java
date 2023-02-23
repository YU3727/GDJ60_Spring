package com.pooh.s1.board;

public interface BoardDAO extends BbsDAO{

	//어떤 기능이 있는가?를 생각 -> List, Detail, Add, Update, delete
	//BbsDAO를 상속받고, BbsDAO에 없는 detail을 추가하자
	//bankbookcomment에는 detail이 없으므로 부모형으로 boardDTO를 쓰면 된다
	
	//list
	
	
	//detail
	public BoardDTO getBoardDetail(BoardDTO boardDTO) throws Exception;
	
	//add
	
	
	//update
	
	
	//delete
	
	
}
