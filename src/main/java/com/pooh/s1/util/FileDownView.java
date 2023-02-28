package com.pooh.s1.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("fileDownView")
public class FileDownView extends AbstractView{
//AbstractView를 상속받은 클래스에 한해서 BeanNameViewResolver가 먼저 탐색을 해본다.
	
	//추상메서드 얘만 만들면 됨.
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("fileDownView");
	}
}
