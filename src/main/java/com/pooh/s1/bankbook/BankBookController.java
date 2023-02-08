package com.pooh.s1.bankbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//속성이 value 한개라면 value를 생략해도 된다. /bankBook/**는 모든 하위폴더를 의미
@RequestMapping(value="/bankBook/*") 
public class BankBookController {

	@Autowired
	private BankBookService bankBookService;
	
	//getBankBookList
	@RequestMapping(value="list")
	public ModelAndView getBankBookList(ModelAndView mv)throws Exception{
		List<BankBookDTO> ar = bankBookService.getBankBookList();
		
		mv.setViewName("/bankBook/list");
		mv.addObject("list", ar);
		return mv;
	}
	
	
}
