package com.pooh.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class MemberService {
//230202 8교시
	
	@Autowired
	private MemberDAO memberDAO;
	
	
	public int setMemberAdd(MemberDTO memberDTO) throws Exception{
		int result = memberDAO.setMemberAdd(memberDTO);
		return result;
	}
}
