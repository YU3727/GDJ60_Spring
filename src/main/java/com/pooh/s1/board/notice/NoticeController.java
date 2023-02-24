package com.pooh.s1.board.notice;

import java.util.List;

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
import com.pooh.s1.board.BoardDTO;
import com.pooh.s1.board.BoardService;
import com.pooh.s1.util.Pager;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	//autowired의 일치 데이터를 찾는 순서 : 1)데이터타입, 2)객체이름(bean명)
	@Autowired
	private NoticeService noticeService;
	
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
	
	
	//add - 주소는 같지만 메서드 형식으로 구별하는 Annotation을 사용해보자
//	@RequestMapping(value = "add", method = RequestMethod.GET)
	@GetMapping("add") //위와 아래는 같음.
	public ModelAndView setBoardAdd() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/add");
		return mv;
	}
	
	@PostMapping("add")
	public ModelAndView setBoardAdd(NoticeDTO noticeDTO) throws Exception{
		//여기서는 noticeDTO에 있는 모든 데이터타입이 있는것을 매개변수로 써야한다. noticeDTO나 boardDTO가 가능. BbsDTO는 x(title이 없음)
		ModelAndView mv = new ModelAndView();
		int result = noticeService.setBoardAdd(noticeDTO);
		//성공실패 여부를 controller에서 변환해서 보내거나, jsp에서 변환시켜 보내는 두가지 방법이 있다.
		//그러나 common/result.jsp는 공통적으로 쓸 목적이기 때문에 controller에서 메시지를 만들어서 보내주는게 맞다.
		String message="등록 실패";
		if(result>0) {
			message="글이 등록 되었습니다";
		}
		
		mv.addObject("result", message);
		mv.addObject("url", "./list"); //notice/list로 감
		mv.setViewName("common/result");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getBoardDetail(NoticeDTO noticeDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardDTO boardDTO = noticeService.getBoardDetail(noticeDTO);
		
		mv.addObject("dto", boardDTO);
		mv.setViewName("board/detail");
		return mv;
	}
	
}
