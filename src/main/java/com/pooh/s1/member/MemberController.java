package com.pooh.s1.member;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	//Ajax 요청을 처리하기위한 메서드
	@PostMapping("memberIdCheck")
	public ModelAndView getMemberIdCheck(MemberDTO memberDTO) throws Exception{
		//login 하는거 가지고 체크가능
		boolean check = memberService.getMemberIdCheck(memberDTO);
		ModelAndView mv = new ModelAndView();
		
		//redirect로 보내면 request에 담은 정보가 다 사라진다. 불가능 남은건 jsp로 보내는것
		//back-end 특성상 요청이 들어오면 응답을 보내야하므로, true/false를 찍을 수 있는 jsp가 필요하다
		mv.addObject("result", check);
		mv.setViewName("common/ajaxResult"); //응답을 member/memberAdd로 해버리면 새 페이지 자체가 응답으로 나감.
		return mv;
	}
	
	
	@RequestMapping(value = "memberAgree", method = RequestMethod.GET) //a태그는 모두 get
	public void setMemberAgree() throws Exception{
		
	}
	
	@RequestMapping(value = "memberUpdate", method = RequestMethod.POST)
	public ModelAndView setMemberUpdate(HttpSession session, ModelAndView mv, MemberDTO memberDTO) throws Exception{
		//id는 로그인 한 사용자의 id를 꺼내오자. 매개변수로 session을 받아옴. Object타입이므로 MemberDTO 타입으로 형변환 해야한다
		MemberDTO sessionMemberDTO = (MemberDTO)session.getAttribute("member");
		//꺼낸 memberDTO의 id를 집어넣어주자
		memberDTO.setId(sessionMemberDTO.getId());
		int result = memberService.setMemberUpdate(memberDTO);
		//수정에 성공했을 때만(result > 0) session을 바꿔주면 된다
//		if(result>0) {
//			session.setAttribute("member", memberDTO);
//		}
		
		mv.setViewName("redirect:./memberPage");
		return mv;	
	}
	
	@RequestMapping(value = "memberUpdate", method = RequestMethod.GET)
	public ModelAndView setMemberUpdate(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		//memberpage와 동일
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		memberDTO = memberService.getMemberPage(memberDTO);
		mv.addObject("dto", memberDTO);
		mv.setViewName("member/memberUpdate");
		return mv;
	}
	
	@RequestMapping(value="memberPage", method = RequestMethod.GET)
	public ModelAndView getmemberPage(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		//session에는 id값만 담겨있기 때문에 나머지 정보를 받아와야한다
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		//이 memberDTO에는 session에 담긴 DTO의 정보가 아니라 위에서 새로 만든 DTO의 정보
		//여기에는 getMemberPage 메서드를 이용해서, 다른 정보들을 불러온다.
		memberDTO = memberService.getMemberPage(memberDTO);
		mv.addObject("dto", memberDTO);
		//session에는 id만 남아있고, ModelAndView
		mv.setViewName("member/memberPage");
		return mv;
	}
	
	@RequestMapping(value = "memberLogout", method = RequestMethod.GET)
	public ModelAndView getMemberLogout(HttpSession session) throws Exception{
		//logout은 session을 없애는 것. session을 써야한다 > 매개변수로 session을 쓸 수 있음
		//1. member를 꺼냈을 떄 null이면 로그아웃처리. 이건 잘 안씀
		//2. session의 메서드 invalidate()를 쓴다(즉시 삭제 효과)
		ModelAndView mv = new ModelAndView();
		session.invalidate(); //session객체의 남은 시간을 0으로 조정
		mv.setViewName("redirect:../");
		return mv;
	}
	
	@RequestMapping(value="memberLogin", method = RequestMethod.GET)
	public ModelAndView memberLogin(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberLogin");
		
		//모든 요청은 request 객체에 담겨져 온다. 쿠키를 꺼내오는것도 마찬가지.
		//여기서 해도 되고, jsp에서 해도 된다. memberLogin.jsp로(백엔드에서 작업할 필요가 없는 경우에는 이렇게 해도 된다)
//		Cookie [] cookies = request.getCookies();
//		
//		for(Cookie cookie : cookies) {
//			//확인용
//			System.out.println(cookie.getName());
//			System.out.println(cookie.getValue());
//			System.out.println(cookie.getDomain());
//			System.out.println(cookie.getPath());
//			System.out.println("---------------");
//			
//			if(cookie.getName().equals("rememberId")) {
//				mv.addObject("rememberId", cookie.getValue());
//				break;
//			}
//		}
		
		return mv;
	}
	
	@RequestMapping(value = "memberLogin", method = RequestMethod.POST)
	public ModelAndView getMemberLogin(MemberDTO memberDTO, HttpServletRequest request, String remember, HttpServletResponse response)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//매개변수 remember를 사용해서 ID 기억하기 기능 구현
//		System.out.println("Remember: "+remember);
		if(remember != null && remember.equals("remember")) {
			//id를 쿠키에 담아서 보내려고 한다 - 세션이 아님(끄고 나가서 차후에 접속하면 세션이 소멸)
			//로그인 성공했을 때만 아이디 기억을 하려고 하면 로그인 단계인 아래쪽에 이 코드를 작성하면 된다
			Cookie cookie = new Cookie("rememberId", memberDTO.getId());
			//쿠키의 수명설정(초 단위), -1인 경우 영구히 저장하라는 의미
			cookie.setMaxAge(60*60*24*7);
			//쿠키도 응답으로 나가기 때문에 response에 쿠키를 담아준다
			response.addCookie(cookie);
		}else {
			//체크 안됐을 때 -> 아이디가 계속 남아있으면 안되므로, 쿠키를 삭제하거나 삭제하는 것 처럼 보이게 한다
			Cookie cookie = new Cookie("rememberId", ""); //덮어씌우기
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		
//		memberDTO = memberService.getMemberLogin(memberDTO);
//		//request에서 session객체를 반환해줌(lifecycle이 작은 객체에서 큰 객체를 꺼내기 가능 - 무조건 존재하기때문)
//		if(memberDTO != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("member", memberDTO);
//		}
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
