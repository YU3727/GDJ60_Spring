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
	
	//id 중복 검증을 위한 service메서드
	public boolean getMemberIdCheck(MemberDTO memberDTO) throws Exception{
		//매개변수에 id를 담아 보내야함
		memberDTO = memberDAO.getMemberLogin(memberDTO);
		
		boolean check = true; //중복 아니면 true, 중복이면 false
		//값이 있으면 가져올거고, 없으면 null일거니까
		if(memberDTO!=null) {
			check = false;
		}
		return check;
	}
	
	
	//setMemberUpdate
	public int setMemberUpdate(MemberDTO memberDTO) throws Exception{
		return memberDAO.setMemberUpdate(memberDTO);
	}
	
//	//getMemberLogin
//	public MemberDTO getMemberLogin(MemberDTO memberDTO)throws Exception{
//		return memberDAO.getMemberLogin(memberDTO);
//	}
	
	//getMemberLogin - pw를 service에서 확인하는 방법
	//230215 
	public MemberDTO getMemberLogin(MemberDTO memberDTO)throws Exception{
		//memberDTO : Client에서 입력한 ID, PW
		MemberDTO result = memberDAO.getMemberLogin(memberDTO);
		//result : ID와 일치하는 모든 정보
		
		//pw check
		//result가 null이 들어왔을때의 exception을 방지하기 위해 앞의 null체크를 먼저 해준다
		if(result != null && memberDTO.getPw().equals(result.getPw())) {
			memberDTO.setPw(null); //여기서 null을 넣으면 DTO에 pw값이 null이 됨
			//memberDTO에 ID만 있음
			memberDTO.setRoleDTO(result.getRoleDTO());
			//이제 memberDTO에 ID와 roleDTO 정보만 담겨있음
			return memberDTO;
		}else {
			return null;
		}
	}
	
	//getMemberPage 
	//230215 memberpage를 활용 - id가지고 일치하는 나머지 정보 불러오기 위한 메서드
	public MemberDTO getMemberPage(MemberDTO memberDTO) throws Exception{
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
