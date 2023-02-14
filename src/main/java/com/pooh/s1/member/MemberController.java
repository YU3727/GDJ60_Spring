package com.pooh.s1.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	
	
	@RequestMapping(value = "memberUpdate", method = RequestMethod.POST)
	public ModelAndView setMemberUpdate(HttpSession session, ModelAndView mv, MemberDTO memberDTO) throws Exception{
		//id는 로그인 한 사용자의 id를 꺼내오자. 매개변수로 session을 받아옴. Object타입이므로 MemberDTO 타입으로 형변환 해야한다
		MemberDTO sessionMemberDTO = (MemberDTO)session.getAttribute("member");
		//꺼낸 memberDTO의 id를 집어넣어주자
		memberDTO.setId(sessionMemberDTO.getId());
		int result = memberService.setMemberUpdate(memberDTO);
		//수정에 성공했을 때만(result > 0) session을 바꿔주면 된다
		if(result>0) {
			session.setAttribute("member", memberDTO);
		}
		
		mv.setViewName("member/memberPage");
		return mv;	
	}
	
	@RequestMapping(value = "memberUpdate", method = RequestMethod.GET)
	public ModelAndView setMemberUpdate(MemberDTO memberDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberUpdate");
		return mv;
	}
	
	
	@RequestMapping(value = "memberLogout", method = RequestMethod.GET)
	public ModelAndView getMemberLogout(HttpSession session) throws Exception{
		//logout은 session을 없애는 것. session을 써야한다 > 매개변수로 session을 쓸 수 있음
		//1. member를 꺼냈을 떄 null이면 로그아웃처리. 이건 잘 안씀
		//2. session의 메서드 invalidate()를 쓴다(즉시 삭제 효과)
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("redirect:../");
		return mv;
	}
	
	@RequestMapping(value="memberPage")
	public ModelAndView myPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberPage");
		return mv;
	}
	
	@RequestMapping(value="memberLogin", method = RequestMethod.GET)
	public ModelAndView memberLogin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberLogin");
		return mv;
	}
	
	@RequestMapping(value = "memberLogin", method = RequestMethod.POST)
	public ModelAndView getMemberLogin(MemberDTO memberDTO, HttpServletRequest request)throws Exception{
		ModelAndView mv = new ModelAndView();
		memberDTO = memberService.getMemberLogin(memberDTO);
		//request에서 session객체를 반환해줌(lifecycle이 작은 객체에서 큰 객체를 꺼내기 가능 - 무조건 존재하기때문)
		HttpSession session = request.getSession();
		session.setAttribute("member", memberDTO);
		mv.setViewName("redirect:../");
		return mv;
	}
	
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
	
}
