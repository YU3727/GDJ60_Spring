package com.pooh.s1.board.notice;

import java.util.List;

import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.board.BoardDAO;
import com.pooh.s1.board.BoardDTO;
import com.pooh.s1.util.Pager;

public class NoticeDAO implements BoardDAO {
	
	//클래스 생성 -> interface 영역에 add를 누르고, 부모 interface를 지정하고 클래스 만들기
	//

	@Override
	public Long getTotalCount(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<BbsDTO> getBoardList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setBoardAdd(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setBoardUpdate(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setBoardDelete(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO getBoardDetail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
