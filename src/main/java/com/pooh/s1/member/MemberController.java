package com.pooh.s1.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pooh.s1.bankbook.BankBookDTO;

@Controller
@RequestMapping(value="/member/*") //얘는 url에만 적용. RequestMapping에서만 생각하기
public class MemberController {
//230202 6교시 MemberController 직접 작성해보기
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "memberList", method = RequestMethod.GET)
	public ModelAndView getMemberList(ModelAndView mv) throws Exception{
		List<MemberDTO> ar = memberService.getMemberList();
		mv.setViewName("member/memberList");
		mv.addObject("list", ar);
		return mv;
	}
	
//	//getMemberDetail
//	@RequestMapping(value="detail", method = RequestMethod.GET)
//	public ModelAndView getMemberDetail(MemberDTO memberDTO, ModelAndView mv) throws Exception{
//		//필요한 데이터를 담음
//		memberDTO = memberService.getMemberDetail(memberDTO);
//		
//		//jsp로 보낼 준비를함
//		mv.setViewName("member/detail");
//		mv.addObject("dto", memberDTO); //이름("DTO")은 JSP내부에서만 데이터를 식별하는 용도로 사용한다.
//		return mv;
//	}
	
	@RequestMapping(value="memberAdd")
	public void setMemberAdd() throws Exception{
	}
	
	//나중에 setMemberAdd 완전히 다 만들고 나서 살려야할듯;
	@RequestMapping(value="memberAdd", method=RequestMethod.POST)
	public ModelAndView setMemberAdd(MemberDTO memberDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = memberService.setMemberAdd(memberDTO);
//		mv.setViewName("/member/memberAdd");
		mv.setViewName("redirect:../"); //Home으로 redirect
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
