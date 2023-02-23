package com.pooh.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pooh.s1.board.BbsDAO;
import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.board.BoardDTO;
import com.pooh.s1.board.BoardService;
import com.pooh.s1.util.Pager;

@Service
public class QnaService implements BoardService{
	
	@Autowired
	private BbsDAO qnaDAO;

	@Override
	public List<BbsDTO> getBoardList(Pager pager) throws Exception {
		pager.makeRow();
		Long count = qnaDAO.getTotalCount(pager);
		pager.makeNum(count);
		return qnaDAO.getBoardList(pager);
	}

	@Override
	public int getBoardAdd(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBoardUpdate(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBoardDelete(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO getBoardDetail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
