package com.pooh.s1.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class MemberService {
//230202 8교시
//230214 2교시
	
	@Autowired
	private MemberDAO memberDAO;
	
	
	//setMemberUpdate
	public int setMemberUpdate(MemberDTO memberDTO) throws Exception{
		return memberDAO.setMemberUpdate(memberDTO);
	}
	
	//getMemberLogin
	public MemberDTO getMemberLogin(MemberDTO memberDTO)throws Exception{
		return memberDAO.getMemberLogin(memberDTO);
	}
	
	//setMemberAdd
	public int setMemberAdd(MemberDTO memberDTO) throws Exception{
		//먼저 member에 add를 하고
		int result = memberDAO.setMemberAdd(memberDTO);
		//두번쨰에 받아온 DTO의 id로 memberRole에 add 하자
		result = memberDAO.setMemberRoleAdd(memberDTO);
		return result;
	}
	
	//getMemberList
	public List<MemberDTO> getMemberList()throws Exception{
		List<MemberDTO> ar = memberDAO.getMemberList();
		return ar;
	}
	
//	//getMemberDetail
//	public MemberDTO getMemberDetail(MemberDTO memberDTO) throws Exception{
//		return memberDAO.getMemberDetail(memberDTO);
//	}
	
}
