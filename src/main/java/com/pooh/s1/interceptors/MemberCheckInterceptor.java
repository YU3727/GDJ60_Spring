package com.pooh.s1.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MemberCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//Controller 진입 전
		//return이 true라면 다음 Controller로 진행, false라면 Controller로 진행X(개발자가 다른곳으로 보내야함, fowarding, redirect)
		System.out.println("Controller 진입 전");
		//회원이면 session에 정보가 있을거고, 있으면 "member" 속성명의 데이터를 가져와라. 없으면 null일것.
		Object obj = request.getSession().getAttribute("member");
		boolean check;
		
		if(obj != null) {
			response.sendRedirect("");
			check = true;
		} else {
			response.sendRedirect("");
			check = false;
		}
		return check;
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
