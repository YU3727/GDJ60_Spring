package com.pooh.s1.interceptors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MemberCheckInterceptor extends HandlerInterceptorAdapter{
	//Spring이 읽어야 하므로 Servlet-context.xml에 interceptor의 위치를 알려줌
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//Controller 진입 전
		//return이 true라면 다음 Controller로 진행, false라면 Controller로 진행X(개발자가 다른곳으로 보내야함, fowarding, redirect)
		//회원이면 session에 정보가 있을거고, 있으면 "member" 속성명의 데이터를 가져와라. 없으면 null일것.
		
		//memberDTO를 넣었지만 session에서 꺼내면 object 타입
		System.out.println("Member Check Interceptor");
		Object obj = request.getSession().getAttribute("member"); //member는 개발자가 입력하는대로
		boolean check;
		
		if(obj != null) {
			//이상 없으면 통과
			return true;
			
		} else {
			//이상 있으면 돌려보냄
			
			//확인용
			System.out.println("로그인 안한 경우");
			
			//요청에 대한 정보 : request / 응답에 대한 정보 : response
			
			//1. Foward Jsp
			//request에 담아보낼것 : alert에 띄울 메시지와 url(common/result)
			//request는 model처럼 Attribute를 담아서 jsp로 보낼 수 있다.
//			request.setAttribute("result", "권한이 없습니다");
//			request.setAttribute("url", "../member/memberLogin"); 
//			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp"); //IRVR로 간다면
//			view.forward(request, response);
//			check = false;
			
			
			//2. Redirect	
			response.sendRedirect("../../../../../member/memberLogin"); //root에서 더이상 올라갈 수 없기때문에 이렇게 해도 ok. 큰 프로젝트는 이런식으로 쓴다
			
		}
		return false;
	}
	
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		//Controller에서 리턴 후 D.S.로 진입 전
//		System.out.println("Controller에서 리턴 후 D.S. 진입 전");
//	}
//	
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("JSP 렌더링 후");
//	}

}
