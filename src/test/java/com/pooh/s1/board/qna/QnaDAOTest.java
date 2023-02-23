package com.pooh.s1.board.qna;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pooh.s1.MyTestCase;
import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.util.Pager;

public class QnaDAOTest extends MyTestCase{
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Test
	public void getBoardList() throws Exception{
		Pager pager = new Pager();
		pager.setNum(3L);
//		long count = qnaDAO.getTotalCount(pager);
//		assertNotEquals(0, count);
		
		pager.setKind("writer");
		pager.setSearch("i");
		pager.makeRow();
		
		List<BbsDTO> ar = qnaDAO.getBoardList(pager);
		assertNotEquals(6, ar.size());
	}
	
}
