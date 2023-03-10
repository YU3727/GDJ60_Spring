package com.pooh.s1.board.qna;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.board.BbsService;
import com.pooh.s1.board.BoardDTO;
import com.pooh.s1.board.BoardFileDTO;
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
		
		
		//번외 - Java에서 외부서버로 요청을 보낼 때 사용하는 객체
		RestTemplate restTemplate = new RestTemplate();
		
		//URL, method형식, parameter, header를 세팅해서 요청을 보내야함
		
		//아래는 문서를 보고 메서드에 보낼 매개변수를 작성해야한다
		//header - 설정하는 몇가지 방법이 있음
		HttpHeaders headers = new HttpHeaders();
		//1. headers.add("header name", "header value"); 
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		//2. headers.set헤더명("헤더값");
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		//parameter(post)
		//인터페이스로 선언, 생성자는 실제객체가 있어야 하므로 다른것으로
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("parameter name", "parameter value");
		params.add("grand_type=", "authorization_code");
		params.add("cliend_id=", "${REST_API_KEY}");
		
		//header와 parameter를 하나의 객체에 담는다
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		//요청 보내기 - request 객체를 아래 restTemplate의 매개변수로 넣음
		//getForEntity/Object, postForEntity/Object로 구분 <T>는 Type을 의미, GET/POST는 순서가 다름에 주의
		//String result = restTemplate.getForObject("https://dummyjson.com/products/1", String.class, request);
		String result = restTemplate.postForObject("https://dummyjson.com/products/1", request, String.class);
		
		System.out.println(result);
		
		return mv;
	}
	
	@GetMapping("add")
	public String setBoardAdd() throws Exception{
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("board/add");
		return "board/add";
	}
	
	@PostMapping("add")
	public ModelAndView setBoardAdd(QnaDTO qnaDTO, MultipartFile [] addFiles, HttpSession session) throws Exception{
		//매개변수명은 add.jsp에서 쓴 input 타입의 name 속성명과 같게
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setBoardAdd(qnaDTO, addFiles, session);
		//보안을 위해서는 원래, 여기서 session에서 로그인한 멤버의 DTO를 꺼내서 매개변수 qnaDTO에 넣어줘야한다.
		
		String message = "등	록에 실패했습니다";
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
	
	@PostMapping("delete")
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
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFileDTO boardFileDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		//fileNum으로 조회하려고 함 -> Mapper
		boardFileDTO = qnaService.getBoardFileDetail(boardFileDTO);
		
		//return 까지는 ModelAndView로 보내는건 같고, 목적지가 FileDown이라는 CustomView로 보내려고 한다.
		//D.S.가 보내주는 것이기 때문에, Spring(D.S.) 또한 CustomView의 존재를 알아야한다.(IRVR은 servlet-context.xml에 등록해둬서 아는 것)
		//servlet-context에 가서 custom view resolver를 만들어주자.
		
		mv.addObject("boardFile", boardFileDTO);
		//Servlet-context.xml에 view의 우선순위를 지정해두었다.
		//AbstractView를 상속받은 클래스에 한해서 BeanNameViewResolver가 먼저 탐색을 해본다.
		mv.setViewName("fileDownView");
		
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView setBoardUpdate(BoardDTO boardDTO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardDTO = qnaService.getBoardDetail(boardDTO);
		
		mv.addObject("dto", boardDTO); //dto라는 속성명으로 보내야 interceptor에서 캐치가능
		mv.setViewName("board/update");
		return mv;
	}
	
	//update에는 파일추가까지 필요하다 + realPath를 받기위해 session(application), 지우려고 하는 fileNum까지
	@PostMapping("update")
	public ModelAndView setBoardUpdate(BoardDTO boardDTO, MultipartFile [] addFiles, HttpSession session, Long [] fileNum) throws Exception{
		ModelAndView mv = new ModelAndView();
		//확인용
//		for(Long fn : fileNum) {System.out.println(fn);}
		
		int result = qnaService.setBoardUpdate(boardDTO, addFiles, session, fileNum);
		
		mv.addObject("result", "수정 성공");
		mv.addObject("url", "./list");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	@PostMapping("boardFileDelete")
	public ModelAndView setBoardFileDelete(Long fileNum) throws Exception{
		//boardFileDelete 만들어둔게 Long type으로 매개변수를 받음
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setBoardFileDelete(fileNum);
		
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		
		return mv;
	}
	
	
	//-------------------------------------------------------------------------
	
	//url처리를 하는게 아니기 때문에 get/postMapping을 하진 않음
	//exception을 처리하는 Annotation을 준다(exception클래스명.class)
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
