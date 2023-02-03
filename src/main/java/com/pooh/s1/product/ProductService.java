package com.pooh.s1.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService { //test용 메서드
//230130 6교시 Service는 DAO로 작업을 하기 전/후처리를 하기 위한 메서드명이다.
//230131 1교시 결합도
//230201 4교시 
	
	//서비스는 DAO에 대해 의존적
	@Autowired
	private ProductDAO pDAO;
	
	//230201 6교시 list 하나를 가져오는 메서드 추가
	public ProductDTO getProductDetail(ProductDTO pDTO) throws Exception{
		return pDAO.getProductDetail(pDTO);
	}
	
	
	//230201 4교시 list 가져오는 메서드 추가
	public List<ProductDTO> getProductList() throws Exception{
		//여기서는 DAO를 호출하자
		return pDAO.getProductList();
	}
	
	
	public int setAddProduct(ProductDTO pDTO, List<ProductOptionDTO> ar) throws Exception{
		//다른클래스의 메서드와 이름이 중복되어도 상관x 다른클래스니까
		//product table에 상품을 등록, option도 등록
		//입력을 다른곳에서 받아올거기 때문에 매개변수가 필요함
		//호출하려면 객체부터 만들어야한다는걸 생각하자
		Long productNum = pDAO.getProductNum();
		pDTO.setProductNum(productNum); //pDTO에는 시퀀스번호가 들어가있지 않으므로 넣어준다.
		int result = pDAO.setAddProduct(pDTO);
		
		if(ar != null) {
			//옵션은 반복해야 하므로 반복문을 돌린다. List에서 꺼낸건 ProductOptionDTO이므로
			for(ProductOptionDTO poDTO : ar) {
				poDTO.setOptionNum(productNum);
				result = pDAO.setAddProductOption(poDTO); //옵션은 최소 1개 이상이므로 List를 사용한다
			}
		}
		return result;
		
	}

}
