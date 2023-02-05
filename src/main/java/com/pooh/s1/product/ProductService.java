package com.pooh.s1.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
//DAO 데이터의 전/후처리를 담당하는 부분(DAO와 데이터를 주고받는 부분)
	
	@Autowired
	private ProductDAO productDAO;
	
	
	//list를 하나 가져오는 메서드
	public ProductDTO getProductDetail(ProductDTO productDTO) throws Exception{
		productDTO = productDAO.getProductDetail(productDTO);
		return productDTO;
	}
	
	
	//list를 전체 가져오는 메서드
	public List<ProductDTO> getProductList() throws Exception{
		List<ProductDTO> ar = productDAO.getProductList();
		return ar;
	}
	
	//setAddProduct와 getProductNum
	//getProductNum에서 받아온 프로덕트번호로 setAddProduct를 해주자
	public int setAddProduct(ProductDTO productDTO, List<ProductOptionDTO> ar) throws Exception{
		
		//productNum을 받아오려면 DAO에서 메서드를 실행해야함
		//사용할 productDTO는 어딘가에서 받아와야 함
		Long productNum = productDAO.getProductNum();
		productDTO.setProductNum(productNum);
		int result = productDAO.setAddProduct(productDTO);
		
		//product에는 option도 넣어줘야함. product1개에 option은 여러개일수 있으니 list를 사용하자.
		//여기서 사용할 list 또한 어딘가에서 받아와야함
		if(ar != null) {
			for(ProductOptionDTO productOptionDTO : ar) {
				productOptionDTO.setOptionNum(productNum);
				result = productDAO.setAddProductOption(productOptionDTO);
			}
		}
		return result;
	}
	
}
