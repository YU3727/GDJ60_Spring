package com.pooh.s1.board;

public interface BoardService extends BbsService{

	//Detail
	//글번호 받아야하는데 글번호는 boardDTO에 있음
	public BoardDTO getBoardDetail(BoardDTO boardDTO) throws Exception;
	
}
