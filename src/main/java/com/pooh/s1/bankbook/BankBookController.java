package com.pooh.s1.bankbook;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pooh.s1.util.Pager;

@Controller
//속성이 value 한개라면 value를 생략해도 된다. /bankBook/**는 모든 하위폴더를 의미
@RequestMapping(value="/bankBook/*") 
public class BankBookController {
//230208 2교시 3교시
	
	@Autowired
	private BankBookService bankBookService;
	
	//getBankBookList
	@RequestMapping(value="list", method = RequestMethod.GET)
	public ModelAndView getBankBookList(Pager pager, ModelAndView mv)throws Exception{
		//parameter와 매개변수의 멤버변수 setter 명이 같으면 데이터를 집어넣어준다.(spring)
		//ModelAndView 객체를 만들어서 쓰거나 매개변수로 받아와서 쓰거나 둘중 하나.

		List<BankBookDTO> ar = bankBookService.getBankBookList(pager);
		
		//이제 JSP로 보내야한다.
		//model에는 내가 보내야할 data를 담고, view에는 jsp 경로를 담아준다
		//view에 jsp경로 담기
		mv.setViewName("bankBook/list");
		//model에 attribute로 데이터 보내기(name, value)
		//name은 jsp에서 데이터를 꺼낼때 사용함.
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		return mv;
	}
	
	
	//getBankBookDetail
	@RequestMapping(value="detail", method = RequestMethod.GET)
	public ModelAndView getBankBookDetail(BankBookDTO bankBookDTO, ModelAndView mv) throws Exception{
		//필요한 데이터를 담음
		bankBookDTO = bankBookService.getBankBookDetail(bankBookDTO);
		
		//jsp로 보낼 준비를함
		mv.setViewName("bankBook/detail");
		mv.addObject("DTO", bankBookDTO); //이름("DTO")은 JSP내부에서만 데이터를 식별하는 용도로 사용한다.
		
		return mv;
	}
	
	
	//setBankBookAdd
	//입력폼으로 보내주는 메서드
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView setBankBookAdd() throws Exception{
		//ModelAndView를 매개변수로 쓰는 경우, DispatcherServlet이 mv를 보내줌.
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bankBook/add");
		return mv;
	}
	
	//parameter를 받아서 DB에 insert 하는 메서드
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView setBankBookAdd(BankBookDTO bankBookDTO, ModelAndView mv, MultipartFile pic, HttpSession session) throws Exception{
		//매개변수인 interface MultipartFile의 이름은 jsp에서 쓰는 input의 속성 name과 일치해야한다
		//확인용
		System.out.println("name :"+pic.getName());
		System.out.println("originalFileName :"+pic.getOriginalFilename());
		System.out.println("fileSize :"+pic.getSize());
		System.out.println(session.getServletContext());
//		pic.getBytes()
		//서버-controller간 동작을 테스트 하려면 Service를 호출하는 메서드는 주석처리하고 테스트.(실제로 업로드 작업 x) ex. 파일업로드 체크 등
		int result = bankBookService.setBankBookAdd(bankBookDTO, pic);
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	//setBankBookDelete
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView setBankBookDelete(ModelAndView mv, BankBookDTO bankBookDTO) throws Exception{
		//지우려면 DB에 다녀와야하고, DB에 접근하려면 DAO를 가야하고, DAO에 가려면 Service에 가야한다.
		int result = bankBookService.setBankBookDelete(bankBookDTO);
		//view에 jsp경로를 담아서 어디로 가야할지 알려줌.
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	//수정 폼으로 이동
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public ModelAndView setBankBookUpdate(BankBookDTO bankBookDTO) throws Exception{
		//매개변수 bankbookDTO는 bookNumber의 정보를 담고있어서, 어느 data를 수정할건지 특정짓게 해줌
		ModelAndView mv = new ModelAndView();
		//detail로 특정DTO의 정보를 다 불러옴
		bankBookDTO = bankBookService.getBankBookDetail(bankBookDTO);
		mv.setViewName("bankBook/update");
		//특정인의 모든데이터가 담겨있는 DTO를 mv에 담아서 보냄
		mv.addObject("dto", bankBookDTO);
		return mv;
	}
	
	//parameter를 받아서 DB에 update 하는 메서드
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView setBankBookUpdate(BankBookDTO bankBookDTO, ModelAndView mv) throws Exception{
		//bookNumber를 받아와야 할수있겠는데?
		int result = bankBookService.setBankBookUpdate(bankBookDTO);
		mv.setViewName("redirect:./list");
		return mv;
	}
}
