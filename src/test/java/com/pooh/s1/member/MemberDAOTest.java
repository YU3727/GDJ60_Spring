package com.pooh.s1.member;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pooh.s1.MyTestCase;

public class MemberDAOTest extends MyTestCase{
//230207 6교시 memberjoin test 만들기
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void setMemberAddTest() throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId("iu0001");
		memberDTO.setPw("iu0001");
		memberDTO.setMemberName("iu");
		memberDTO.setMemberPhone("01011112222");
		memberDTO.setEmail("iu@gmail.com");
		
		int result = memberDAO.setMemberAdd(memberDTO);
		assertEquals(1, result);
	}
	
}
