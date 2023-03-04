package com.pooh.s1.bankbook;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.board.BbsService;
import com.pooh.s1.member.MemberDTO;
import com.pooh.s1.util.Pager;

@Controller
@RequestMapping("/bankBookComment/*")
public class BankBookCommentController {

	@Autowired
	private BankBookCommentService bankBookCommentService;
	
	@ModelAttribute("boardName")
	public String getBoardName() {
		return "BankBookComment";
	}
	
	
	//댓글 페이징처리 해야한다 생각해보자
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getBoardList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		//pager에 kind, search, page, booknum을 받아서 보냄
		List<BbsDTO> ar = bankBookCommentService.getBoardList(pager);
		
		mv.addObject("list", ar);
		mv.setViewName("common/commentList"); //수업을 위해 notice와 같은곳으로 보냄. 나중에 수정
		
		return mv;
	}
	
	//Ajax 요청을 처리하기 위한 메서드
	@PostMapping("add")
	public ModelAndView setBoardAdd(BankBookCommentDTO bankBookCommentDTO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//session에서 writer 꺼내기
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
//		bankBookCommentDTO.setWriter(memberDTO.getId());
		bankBookCommentDTO.setWriter("iu");
		
		int result = bankBookCommentService.setBoardAdd(bankBookCommentDTO, null, null);
		//bookNumber, contents는 parameter로 넘어옴.(jsp에서 설정)
		
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	
}
