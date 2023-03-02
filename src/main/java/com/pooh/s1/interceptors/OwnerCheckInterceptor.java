package com.pooh.s1.interceptors;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pooh.s1.board.BoardDTO;
import com.pooh.s1.board.notice.NoticeDAO;
import com.pooh.s1.board.qna.QnaDAO;
import com.pooh.s1.member.MemberDTO;

@Component
public class OwnerCheckInterceptor extends HandlerInterceptorAdapter{

	//DB에서 작성자 정보를 조회하기 위해서 DAO를 멤버변수로 선언 - qna,notice 모두사용해야한다
	@Autowired
	private QnaDAO qnaDAO;
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//'작성자'에 대한 정보가 필요하다. 하지만 parameter로 받아오는건 num. => 번호(num)만 가지고 작성자를 어떻게 알까?
		Long num = Long.parseLong(request.getParameter("num"));
		//qna이나 notice인지 구분을 해야함
		//uri(서버 내에서 구분에 필요한 식별자)를 사용(uri = url - (ip + port))
		String uri = request.getRequestURI();
		uri = uri.substring(1, uri.lastIndexOf("/"));
		
		//qna, notice 둘 다 boardDTO를 받아옴, 파라미터로 받아온 num을 세팅해서 넣어줌
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(num);
		
		//notice, qna 구분해서 DTO 만들고 
		if(uri.equals("notice")) {
			boardDTO = noticeDAO.getBoardDetail(boardDTO);
		}else {
			boardDTO = qnaDAO.getBoardDetail(boardDTO);
		}
		
		//memberDTO는 session에 담긴 로그인 정보에서 데이터를 꺼내온 것(속성명 member)
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		
		//로그인한 id와 DB에서 조회해 온 writer(작성자)를 비교
		if(!memberDTO.getId().equals(boardDTO.getWriter())) {
			request.setAttribute("result", "작성자만 가능");
			request.setAttribute("url", "./list");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			view.forward(request, response);
			
			return false;
		}
		
		return true;
	}
	
	
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		//얘는 ModelAndView에 뭔가 수정이 가능하다
//			
//		System.out.println("Owner Check Interceptor");
//		
//		//1. 로그인사용자 확인 (session에 있는 멤버DTO의 정보 꺼내오기)
//		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
//		
//		//2. 작성자 확인 - 최종적으로 원하는 타입은 BoardDTO
//		//boardDTO를 ModelAndView에서 꺼내려고 함.
//		//ModelAndView에서 model을 꺼내면 return이 map. map에 담고
//		Map<String, Object> map = modelAndView.getModel();
//		//map에서 속성명 "dto"로 꺼내서 boardDTO에 담음
//		BoardDTO boardDTO = (BoardDTO)map.get("dto");
//		
//		//3. check
//		if(memberDTO == null || !boardDTO.getWriter().equals(memberDTO.getId())) {
//			//postHandle은 return이 void라 응답이 나가는걸 막을 수 없다.
//			//응답을 수정하는 방향으로...
//			
//			modelAndView.setViewName("common/result");
//			modelAndView.addObject("result", "작성자가 아닙니다");
//			modelAndView.addObject("url", "./list");
//		}
//		
//		
//		//열거형(Iterator)으로 Map의 key값을 꺼내보기(필요한경우)
////		Iterator<String> it = map.keySet().iterator();
////			while(it.hasNext()) {
////				String key = it.next();
////				System.out.println("Key : "+key);
////		}
//		 
//	}
}
