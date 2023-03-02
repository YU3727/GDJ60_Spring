package com.pooh.s1.product;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pooh.s1.MyTestCase;
import com.pooh.s1.util.Pagination;

public class ProductDAOTest extends MyTestCase {
//230207 5교시 Test
	
	@Autowired //주입
	private ProductDAO productDAO; //선언
	
//	@Test
	public void getProductListTest(Pagination pagination) throws Exception{
		//실행할 때 우클릭 - Run as - JUnit Test라고 해야함.
		List<ProductDTO> ar = productDAO.getProductList(pagination);
		
		//단정문(TestCase에는 이런게 있다)
		//ar의 size가 0이 아니길 희망한다   -> print문 대신
		assertNotEquals(0, ar.size()); 
		//JUnit의 결과로 Failures는 단정문으로 작동시킨것이 0으로 왔다는 의미
		
		assertEquals(0, ar.size()); 
	}
	
//	@Test
	public void getProductDetailTest() throws Exception{
		//test할때는 client한테 dto를 받아올게 아니니까 여기서 만들어서 해보자
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setProductNum(20000L);
		//productNum이 20000이 없을거라 예상함
		productDTO = productDAO.getProductDetail(productDTO);
		
		assertNull(productDTO);
		
	}
	
	//insert test
//	@Test
	public void setProductAddTest() throws Exception{
		for(int i=1;i<=30;i++) {
			ProductDTO productDTO = new ProductDTO();
			Random r = new Random();
			double d = r.nextDouble();
			int num = (int)(d*1000);
			d = num/100.0;
			
//			productDTO.setProductNum(100L);
			productDTO.setProductName("product"+i);
			productDTO.setProductDetail("detail"+d);	
			int result = productDAO.setProductAdd(productDTO);
		}
		
//		assertEquals(1, result);
	}
}
