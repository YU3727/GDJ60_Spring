package com.pooh.s1.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
//230202 6교시
	//객체 생성 위임의 2가지 방법 @Annotation / XML
	
	//해야할 일이 list, add, update, delete 등이 있으므로, 얘들을 실행할 메서드를 하나씩 만들자
	@RequestMapping(value = "/product/list")
	public void getProductList() {
		System.out.println("Product List");
	}
	
	@RequestMapping("/product/detail")
	public void getProductDetail() {
		System.out.println("Product Detail");
	}
}
