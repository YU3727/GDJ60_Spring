package com.pooh.s1.bankbook;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pooh.s1.MyTestCase;
import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.util.Pager;


public class BankBookCommentDAOTest extends MyTestCase{
	
	@Autowired
	private BankBookCommentDAO bankBookCommentDAO;
	
	//test할 메서드 작성
	//list
//	@Test
	public void getBoardListTest() throws Exception{
		Pager pager = new Pager();
		pager.setBookNumber(385L);
//		long count = bankBookCommentDAO.getTotalCount(pager);
		
		pager.setKind("contents");
		pager.setSearch("U");
		pager.makeRow(); //startRow, lastRow를 계산
		
		List<BbsDTO> ar = bankBookCommentDAO.getBoardList(pager);
		assertNotEquals(0, ar.size());
	}
	
	
}
