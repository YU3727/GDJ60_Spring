package com.pooh.s1.interceptors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pooh.s1.member.MemberDTO;

@Component
public class AdminCheckInterceptor extends HandlerInterceptorAdapter{

	//role이 Admin이면 통과, 아니면 home으로
	
	//list, detail은 빼고, notice에 add, update, delete만 -> mapping으로
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean check = false;
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		String role = memberDTO.getRoleDTO().getRoleName();
		System.out.println(role);		
		
		
		if(role.equals("ADMIN")) {
			check = true;
			
		}else {
			
			System.out.println("ADMIN이 아님");
			
			request.setAttribute("result", "ADMIN 권한이 아닙니다");
			request.setAttribute("url", "../../../");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/result.jsp");
			view.forward(request, response);
			
			check = false;
			
		}
		
		return check;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}
