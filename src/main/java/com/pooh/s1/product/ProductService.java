package com.pooh.s1.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
//DAO 데이터의 전/후처리를 담당하는 부분(DAO와 데이터를 주고받는 부분)
	
	@Autowired
	private ProductDAO productDAO;
	
}
