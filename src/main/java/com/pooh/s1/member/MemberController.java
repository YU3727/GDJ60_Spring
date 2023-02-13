package com.pooh.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/member/*") //얘는 url에만 적용. RequestMapping에서만 생각하기
public class MemberController {
//230202 6교시 MemberController 직접 작성해보기
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="memberAdd")
	public void setMemberAdd() throws Exception{
	}
	
	//나중에 setMemberAdd 완전히 다 만들고 나서 살려야할듯;
	@RequestMapping(value="memberAdd", method=RequestMethod.POST)
	public ModelAndView setMemberAdd(MemberDTO memberDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("./memberAdd");
//		String address = "redirect:./list"; //멤버리스트는 나중에 페이지 추가해야함.
		return mv;
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
