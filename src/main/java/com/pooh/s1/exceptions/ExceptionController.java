package com.pooh.s1.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//interceptor랑 exceptioncontroller는 배포직전에 처리
//@ControllerAdvice
public class ExceptionController {

	//@ControllerAdvice : 예외를 전문적으로 처리하는 Controller Annotation
	//프로젝트내에서 발생하는 모든 Exception은 전부 여기서 처리한다
	
	//유형별로 처리하고 싶으면 해당 class 내에 여기에 메서드를 만들어주면 된다
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView fixException() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "잘못된 접근입니다<br>관리자에게 문의 하세요");
		mv.setViewName("common/error_500");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView fixException2() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "잘못된 접근입니다<br>관리자에게 문의 하세요");
		mv.setViewName("common/error_500");
		
		return mv;
	}
	
	@ExceptionHandler(Throwable.class)
	public ModelAndView fixException3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "잘못된 접근입니다<br>관리자에게 문의 하세요");
		mv.setViewName("common/error_500");
		
		return mv;
	}
	
}
