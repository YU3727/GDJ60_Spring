package com.pooh.s1.board.qna;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.board.BbsService;
import com.pooh.s1.board.BoardDTO;
import com.pooh.s1.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("boardName")
	public String getBoardName() {
		//모든 메서드에 공통적으로 들어가는 속성, 컨트롤러의 어떠한 메서드가 실행되기 전에 가장 먼저 실행된다
		//이 qna를 ModelAndView에 담게끔 스프링에게 시켜주면 됨(-> Annotation으로)
		return "qna";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getBoardList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BbsDTO> ar = qnaService.getBoardList(pager);
		
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("add")
	public String setBoardAdd() throws Exception{
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("board/add");
		return "board/add";
	}
	
	@PostMapping("add")
	public ModelAndView setBoardAdd(QnaDTO qnaDTO, MultipartFile [] files, HttpSession session) throws Exception{
		//매개변수명은 add.jsp에서 쓴 input 타입의 name 속성명과 같게
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setBoardAdd(qnaDTO, files, session);
		
		String message = "등록에 실패했습니다";
		if(result > 0) {
			message = "글 등록 성공!";
		}
		
		//여기에 쓰는 이름은 공통적으로 쓰는 jsp에서 받는 변수명과 일치시켜야함 그래야 jsp에서 데이터를 꺼내주니까
		mv.addObject("result", message);
		mv.addObject("url", "./list"); //글 등록하고 나서도 url 주소는 /qna/add니까 list 주소를 담아서 jsp로 보내주면 거기서 보내준다
		mv.setViewName("common/result");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getBoardDetail(QnaDTO qnaDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardDTO boardDTO = qnaService.getBoardDetail(qnaDTO);
		
		mv.addObject("dto", boardDTO);
		mv.setViewName("board/detail");
		return mv;
	}
	
	
	//답글을 다는 기능
	@GetMapping("reply")
	public ModelAndView setReplyAdd(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		//어느 글에대한 답글인가를 알기 위해 멤버변수 num이 필요함 -> num을 포함하는 모든 dto 사용 가능하나, boardDTO에 담음
		//매개변수 앞에 @ModelAttribute가 생략되어있으므로 따로 담지않아도 jsp에서 사용할 수 있다
		//jsp에서 사용할 이름은 매개변수 타입의 첫글자를 소문자로 바꾼것이 된다.(여기서는 boardDTO.num으로 꺼내면 됨)
		//여기서 받아오는 num은 부모의 글번호 num을 받아오는것인가? ok
		
		mv.setViewName("board/reply");
		return mv;
	}
	
	//답글을 다는 기능
	@PostMapping("reply")
	public ModelAndView setReplyAdd(QnaDTO qnaDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = qnaService.setReplyAdd(qnaDTO);
		String message = "글 등록 실패";
		if(result > 0) {
			message = "글 등록 성공";
		}
		
		mv.addObject("result", message);
		mv.addObject("url", "./list");
//		아래는 부모글로 돌아가기
//		mv.addObject("url", "./detail?num="+qnaDTO.getNum());
		mv.setViewName("common/result");
		return mv;
	}
	
	@GetMapping("delete")
	public ModelAndView setBoardDelete(BbsDTO bbsDTO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/result");
		
		int result = qnaService.setBoardDelete(bbsDTO, session);
		String message = "삭제 실패";
		if(result > 0) {
			message = "삭제 성공";
		}
		
		//common/result.jsp로
		mv.addObject("result", message);
		mv.addObject("url", "./list");
		return mv;
		//이렇게하면 파일 없을 때는 에러 뜨는데, table 만들 때 FK CONSTRAINT 걸때 ON DELETE CASCADE 걸거나 ON DELETE SET NULL; 추가해줌
		//요즘에는 글 제목을 바꾸고, 열어볼수 없게끔 한다.(실제로는 UPDATE)
		//회원 탈퇴도 마찬가지로 UPDATE를 통해 사용할 수 없게 만든다.
	}
}
