package com.pooh.s1.board.notice;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pooh.s1.MyTestCase;
import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.util.Pager;

public class NoticeDAOTest extends MyTestCase{

	@Autowired
	private NoticeDAO noticeDAO;
	
//	@Test
	public void getBoardListTest() throws Exception{
		Pager pager = new Pager();
		pager.setNum(1L);
//		long count = noticeDAO.getTotalCount(pager);
//		assertNotEquals(0, count);
		
		pager.setKind("writer");
		pager.setSearch("U");
		pager.makeRow();
		
		List<BbsDTO> ar = noticeDAO.getBoardList(pager);
		assertNotEquals(0, ar.size());
	}
	
	@Test
	public void setBoardAddTest() throws Exception{
		Random r = new Random();
		double d = r.nextDouble();
		int num = (int)(d*1000);
		d = num/100.0;
		
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setTitle("Here comes new owner");
		noticeDTO.setWriter("writer1");
		noticeDTO.setContents("contents1");
		int result = noticeDAO.setBoardAdd(noticeDTO);
		assertEquals(1, result);
	}
}
