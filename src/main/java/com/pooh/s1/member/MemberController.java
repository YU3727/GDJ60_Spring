package com.pooh.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/member/*") //얘는 url에만 적용. RequestMapping에서만 생각하기
public class MemberController {
//230202 6교시 MemberController 직접 작성해보기
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="memberJoin")
	public String memberJoin() throws Exception{
		//test용
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setID("ID1");
		memberDTO.setPW("PW1");
		memberDTO.setMemberName("NAME1");
		memberDTO.setMemberPhone("PHONE1");
		memberDTO.setEmail("EMAIL1");

		int result = memberService.memberJoin(memberDTO);
		System.out.println(result>0);
		String address = "member/memberJoin";
		return address;
	}
	
	@RequestMapping(value="memberLogin")
	public void memberLogin() {
		
	}
	
	@RequestMapping(value="memberPage")
	public ModelAndView myPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberPage");
		return mv;
	}
	
}
