package com.pooh.s1.interceptors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pooh.s1.member.MemberDTO;

public class AdminCheckInterceptor extends HandlerInterceptorAdapter{

	//role이 Admin이면 통과, 아니면 home으로
	
	//list, detail은 빼고, notice에 add, update, delete만 -> mapping으로
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		
		if(memberDTO != null) {
			if(memberDTO.getRoleDTO().getRoleName().equals("ADMIN")) {
				return true;
			}
			//로그인은 했는데 admin이 아닌경우
			
			
		}
		//로그인이 안된 경우, 또는 admin이 아닌 경우
		System.out.println("로그인 안함");
		
		//fowarding
		request.setAttribute("result", "권한이 없습니다");
		request.setAttribute("url", "../../../../");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
		view.forward(request, response);
			
		return false;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}
