package com.pooh.s1.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//별칭을 주려면 @Controller("c") 이런식으로. 별칭을 주지 않으면 bean name은 클래스이름의 첫글자를 소문자로 바꾼것을 기본으로 한다.
@Controller
@RequestMapping(value="/product/*") //root 밑에 product/로 시작하는 모든 것들은 다 ProductController로 보내달라는 의미
public class ProductController {
//230202 6교시
//230203 1교시
	
	//객체 생성 위임의 2가지 방법 @Annotation / XML
	//Controller는 외부로부터 url을 받아서 어느 메서드를 실행할건지를 결정하고
	//해당 메서드의 결과를 jsp로 뿌려주는역할을 한다. 그럼 어느주소를 어디에 연결해야할지 덜 헷갈릴 수 있다.
	
	@Autowired
	private ProductService productService;
	
	//해야할 일이 list, add, update, delete 등이 있으므로, 얘들을 실행할 메서드를 하나씩 만들자
	@RequestMapping(value="list") //여기는 입력받은 URL
	public ModelAndView getProductList(ModelAndView mv) throws Exception{
		//list 또한 jsp로 보내려고 하는것.
		//model에 담는 법 또한 두가지이다. 매개변수로 Model을 넣거나, ModelAndView 객체를 만들어서 거기에 넣거나, ModelAndView를 매개변수로 넣거나... < 이건 D.S.가 객체를 만들어줌
		//ModelAndView mv = new ModelAndView();
		
		List<ProductDTO> ar = productService.getProductList();
		System.out.println(ar.size()>0);
		//실질적으로 필요한 JSP의 주소는 /WEB-INF/views/product/productList.jsp이지만
		//DispatcherServlet에 내장된 IRVR가 prefix = "/WEB-INF/views/", suffix = ".jsp"를 자동으로 추가해준다
		//즉, prefix + 입력하는 문자열 + suffix로 주소를 완성해주기 때문에 리턴할 문자열로는 사이값인 product/productList만 넣으면 된다.
//		String address = "product/productList"; //찾아갈 JSP 주소
//		return address;
		
		mv.setViewName("product/productList");
		mv.addObject("list", ar); //얘는 model.addAttribute와 같은 역할
		
		return mv;
	}
	
	//230203 1교시 이제껏 배운것 + 매개변수 사용방법 첫번째(내장객체 request, response)
//	@RequestMapping(value="detail") //여기서 url에 따라 실행할 메서드 결정
//	//외부에서 넘어온 요청을 적용시키는 1번째 방법. 매개변수정보가 담겨있는 requset, response 객체가 필요하다.
//	public String getProductDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		String num = request.getParameter("num"); //이름이 num인 이유는 jsp에서 ?뒤의 parameter로 num을 줬기 때문
//		//parameter를 확인하려면 크롬 - f12 개발자모드 - Network탭 - f5(새고로침) - 뜨는 Name 클릭 - Payload탭 - parameter 확인 과정을 거쳐야한다.
//
//		System.out.println("Product Detail");
//		//데이터를 담을 봉투가 없으니까 봉투를 만들어주자
//		ProductDTO productDTO = new ProductDTO();
//		
//		//봉투안에 아무것도 없으니까 일단 뭐라도 넣어서 테스트해보자
//		productDTO.setProductNum(10L);
//		//이제 봉투에 매개변수로 받아온 데이터(num)를 넣어보자
//		productDTO.setProductNum(Long.parseLong(num));
//		
//		//이건 메서드 호출인데 이걸 productDTO에 담는다?
//		productDTO = productService.getProductDetail(productDTO);
//		
//		System.out.println(productDTO != null); //이게 true가 뜨면 정상
//		
//		return "product/productDetail"; //실행된 결과를 jsp로 보내줌
//	}
	
	//230203 2교시 이제껏 배운것 + 매개변수 사용방법 두번째(parameter와 매개변수의 이름을 일치)
//	@RequestMapping(value="detail")
//	//외부에서 넘어온 요청을 적용시키는 2번째 방법. 외부로부터 넘어온 Parameter의 이름과, 매개변수의 이름을 일치시키면 그냥 쓸수있다.
//	public String getProductDetail(long num, @RequestParam(value="n") String name, @RequestParam(value="a", defaultValue = "1", required = false) Integer age) throws Exception{
//		//parameter로 null이 올 수 있으므로, int 대신에 reference type인 Integer로 선언하는게 좋다.
//		//@RequestParam의 속성값의 의미 : value = 받는 parameter의 명이 매개변수와 다를때 연결하기위해 사용. defaultvalue = 값이 안들어왔을 때 넣어줄 기본값, required = false : null이 들어오면 data를 받지않겠다는 의미)
//		
//		System.out.println("Product Detail");
//		//데이터를 담을 봉투가 없으니까 봉투를 만들어주자
//		ProductDTO productDTO = new ProductDTO();
//		
//		//이제 봉투에 매개변수로 받아온 데이터(num)를 넣어보자
//		productDTO.setProductNum(num);
//		System.out.println(name);
//		System.out.println(age);
//		
//		//이건 메서드 호출인데 이걸 productDTO에 담는다?
//		productDTO = productService.getProductDetail(productDTO);
//		
//		System.out.println(productDTO != null); //이게 true가 뜨면 정상
//		
//		return "product/productDetail"; //실행된 결과를 jsp로 보내줌
//	}
	
	//230203 3교시 
	@RequestMapping(value="detail")
	//외부에서 넘어온 요청을 적용시키는 3번째 방법. 개념만 이해하면 제일 간단하겠다.
	public String getProductDetail(ProductDTO productDTO, Model model) throws Exception{
		//parameter의 이름과 DTO내부 setter의 이름이 같으면 Spring에서 자동으로 넣어준다.
		//Model 타입 또한 request처럼 jsp까지 살아가고, 응답이 나가면 소멸한다.
		//modet의 역할 : request와 유사함. 

		System.out.println("Product Detail");
		//데이터를 담을 봉투가 없으니까 봉투를 만들어주자
//		ProductDTO productDTO = new ProductDTO();
		
		//이건 메서드 호출인데 이걸 productDTO에 담는다?
		productDTO = productService.getProductDetail(productDTO);
		
		System.out.println(productDTO != null); //이게 true가 뜨면 정상
		
		//가지고 온 attribute(data)를 내보내기전에 model에 넣는다
		//name은 개발자 마음대로 이름을 정하고, value는 보낼 데이터를 넣는다
		model.addAttribute("dto", productDTO);
		
		return "product/productDetail"; //실행된 결과를 jsp로 보내줌
	}
	
	
	@RequestMapping(value="productAdd", method = RequestMethod.GET)  //얘는 해당url과 get메서드가 왔을때. 안써도 작동하니까 default는 get이다
	public void productAdd() {
		//return 값이 void라도 Spring이 친절하게 찾아준다
		//url 경로를 jsp 경로명으로 대체해서 자동으로 찾아줌....
		//즉, url경로와 jsp경로가 일치하는 경우 void로 해도 jsp를 찾아준다.
	}
	
	//230203 7교시 html을 통해 데이터를 입력받고 전달하기
	//2교시에 했던 외부에서 넘어온 요청을 Controller가 접수하는 방법과 같다.
	@RequestMapping(value="productAdd", method = RequestMethod.POST) //얘는 해당url과 post메서드가 왔을때.
	public String productAdd(ProductDTO productDTO) throws Exception{ 
		//parameter 이름과 DTO의 멤버변수 이름이 같아야한다.
		int result = productService.setAddProduct(productDTO, null);
		System.out.println(result == 1); //확인용
		return "redirect:./list"; //끝나고 나서 다른경로로 나가라는 것. 절대경로, 상대경로 모두 사용 가능
	}
	
	//위의 메서드는 이 메서드와 같음.
//	@RequestMapping(value="productAdd")
//	public String productAdd() {
//		return "product/productAdd";
//	}
	
	@RequestMapping(value="update") 
	public ModelAndView productUpdate() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("product/productUpdate");
		return mv;
	}
	
}
