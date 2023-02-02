package com.pooh.s1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller //controller 역할을 하는 객체 생성
public class HomeController {
//230202 2교시 POJO(Plain Old Java Object)
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//요청경로가 root, 요청메서드가 GET일때 아래 메서드를 실행하세요
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//요청이 왔을때(value = "이 메서드가 실행할때 입력되야될 url"
	//servlet은 Spring이 만들어준다.
	@RequestMapping(value = "/test/test.do")
	public void test() {
		System.out.println("/test/test.do test ---------");
	}
	
	@RequestMapping(value = "test/sub.do")
	public void test2() {
		System.out.println("/test/sub.do test2-----------");
	}
	
}
