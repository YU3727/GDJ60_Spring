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
		
		//옵션은 반복해야 하므로 반복문을 돌린다. List에서 꺼낸건 ProductOptionDTO이므로
		for(ProductOptionDTO poDTO : ar) {
			poDTO.setOptionNum(productNum);
			result = pDAO.setAddProductOption(poDTO); //옵션은 최소 1개 이상이므로 List를 사용한다
		}
		
		return result;
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//ㅋㅋㅋ
		ProductDAO pDAO = new ProductDAO();
		ProductDTO pDTO = new ProductDTO();
		
		pDTO.setProductName("product1");
		pDTO.setProductDetail("detail1");
//		pDTO.setProductScore(0.0); //이렇게 하지말고 처음에 쿼리문 넣을때 default로 0.0으로 주자
		
		ProductOptionDTO poDTO = new ProductOptionDTO();
		poDTO.setOptionName("optionName1");
		poDTO.setOptionPrice(100);
		poDTO.setOptionStock(10);
		poDTO.setProductNum(null);
		
		poDTO = new ProductOptionDTO();
		poDTO.setOptionName("optionName2");
		poDTO.setOptionPrice(200);
		poDTO.setOptionStock(20);
		poDTO.setProductNum(null);
		
		int result = 0;
		
		try {
			//커넥션
//			Connection connection = DBConnection.getConnection();
//			System.out.println(connection != null); //true가 나오면 성공
			
			//setAddMember 테스트
//			result = mDAO.setAddMember(mDTO);
//			System.out.println(result != 0);
			
			//setAddProduct 테스트
//			result = pDAO.setAddProduct(pDTO);
//			System.out.println(result != 0);
			
			//getProductList 테스트
			List<ProductDTO> ar = pDAO.getProductList();
			System.out.println(ar.size() != 0);
			
			
			//setAddProductOption 테스트
			//getProductNum은 시퀀스번호를 먼저 만들고 집어넣은 다음에 프로덕트를 만듬
			Long num = pDAO.getProductNum();
			pDTO.setProductNum(num);
			result = pDAO.setAddProduct(pDTO);
			//productnum은 시퀀스로 가져오기 때문에
			
			//제일 최근에 등록한 Product를 자동으로 찾기위해 getMax 사용
			//getMax는 프로덕트를 만들고 시퀀스번호를 가져와야했음
//			Long num = pDAO.getMax(); 
//			poDTO.setProductNum(num);
			
			if(result > 0) {
				pDAO.setAddProductOption(poDTO);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
