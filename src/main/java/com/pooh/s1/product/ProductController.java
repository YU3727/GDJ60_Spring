package com.pooh.s1.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/product/*")
public class ProductController {
//Service와 데이터통신하는 부분
//Server <-> Controller <-> Service <-> DAO <-> DB 
	
	@Autowired
	private ProductService productService;
	
	//해야할 일 : list, add, update, delete 등 -> 각각을 실행할 메서드를 제작
	//외부로부터 req, res객체를 받거나, ModelAndView 객체를 받아와서 쓰자
	//getProductList
	@RequestMapping(value="list")
	public ModelAndView getProductList(ModelAndView mv) throws Exception{
		
		List<ProductDTO> ar = productService.getProductList();
		
		mv.setViewName("product/productList");
		mv.addObject("list", ar);
		
		return mv;	
	}
	
	
	//getProductDetail
	@RequestMapping(value="detail")
	public String getProductDetail(ProductDTO productDTO, Model model) throws Exception{
		
		//detail을 담을 데이터봉투가 필요함 -> 매개변수
		productDTO = productService.getProductDetail(productDTO);
		
		//controller에서 jsp로 데이터를 보낼 봉투가 필요함 -> 매개변수
		//Model, ModelAndView에 넣으면 됨, attribute(이름, 값)
		model.addAttribute("dto", productDTO);
		
		return "product/productDetail";
	}
	
	
	//productAdd
	@RequestMapping(value="productAdd")
	public void productAdd() {
		//url과 jsp주소가 같은경우 void로 생략해도 값을 돌려줌
	}
	
	
	//add 완료 후 list 페이지로 리다이렉트 하고싶은 경우
//	@RequestMapping(value="productAdd", method = RequestMethod.POST)
//	public String productAdd(ProductDTO productDTO) throws Exception{
//		//parameter의 이름과 DTO멤버변수의 이름이 같아아햠
//		int result = productService.setAddProduct(productDTO, null);
//		System.out.println(result == 1);
//		
//		return "redirect:./list";
//	}
	
	
	@RequestMapping(value="update")
	public ModelAndView productUpdate() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("product/productUpdate");
		return mv;
	}
	
}
