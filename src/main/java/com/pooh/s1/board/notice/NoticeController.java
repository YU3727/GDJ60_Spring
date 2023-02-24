package com.pooh.s1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.board.BbsService;
import com.pooh.s1.util.Pager;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	//autowired의 일치 데이터를 찾는 순서 : 1)데이터타입, 2)객체이름(bean명)
	@Autowired
	private BbsService noticeService;
	
	@ModelAttribute("boardName")
	public String getBoardName() {
		return "notice";
	}
	
	
	//list
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getBoardList(@ModelAttribute Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		//pager에 kind, serach, page를 보냄
		List<BbsDTO> ar = noticeService.getBoardList(pager);
		
		//jsp 주소 추가도 필요
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		return mv;
	}
	
}
