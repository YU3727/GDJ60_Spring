package com.pooh.s1.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/product/*") //root 밑에 product/로 시작하는 모든 것들은 다 ProductController로 보내달라는 의미
public class ProductController {
//230202 6교시
	//객체 생성 위임의 2가지 방법 @Annotation / XML
	//Controller는 외부로부터 url을 받아서 어느 메서드를 실행할건지를 결정하고
	//해당 메서드의 결과를 jsp로 뿌려주는역할을 한다. 그럼 어느주소를 어디에 연결해야할지 덜 헷갈릴 수 있다.
	
	@Autowired
	private ProductService productService;
	
	//해야할 일이 list, add, update, delete 등이 있으므로, 얘들을 실행할 메서드를 하나씩 만들자
	@RequestMapping(value="list") //여기는 입력받은 URL
	public String getProductList() throws Exception{
		
		List<ProductDTO> ar = productService.getProductList();
		System.out.println(ar.size()>0);
		//실질적으로 필요한 JSP의 주소는 /WEB-INF/views/product/productList.jsp이지만
		//DispatcherServlet에 내장된 IRVR가 prefix = "/WEB-INF/views/", suffix = ".jsp"를 자동으로 추가해준다
		//즉, prefix + 입력하는 문자열 + suffix로 주소를 완성해주기 때문에 리턴할 문자열로는 사이값인 product/productList만 넣으면 된다.
		String address = "product/productList"; //찾아갈 JSP 주소
		return address;
	}
	
	
	@RequestMapping(value="detail") //여기서 url에 따라 실행할 메서드 결정
	public String getProductDetail() {
		System.out.println("Product Detail");
		return "product/productDetail"; //실행된 결과를 jsp로 보내줌
	}
	
	
	@RequestMapping(value="productAdd")
	public void productAdd() {
		//return 값이 void라도 Spring이 친절하게 찾아준다
		//url 경로를 jsp 경로명으로 대체해서 자동으로 찾아줌....
		//즉, url경로와 jsp경로가 일치하는 경우 void로 해도 jsp를 찾아준다.
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
