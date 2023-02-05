package com.pooh.s1.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
//Service와 데이터통신하는 부분
//Server <-> Controller <-> Service <-> DAO <-> DB 
	
	@Autowired
	private ProductService productService;
	
	
}
