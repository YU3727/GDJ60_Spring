package com.pooh.s1.bankbook;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pooh.s1.MyTestCase;

public class BankBookDAOTest extends MyTestCase{
//230207 8교시
	
	@Autowired
	private BankBookDAO bankBookDAO;
	
	//getBankBookList
	//@Test
//	public void getBankBookListTest() throws Exception{
//		List<BankBookDTO> ar = bankBookDAO.getBankBookList();	
//		assertEquals(0, ar.size());
//	} //얘는 OK
	
	//@Test
	public void getBankBookDetailTest() throws Exception{
		//test할때는 client로부터 받아올수가 없으므로 직접 객체를 만들고 테스트 해보자
		BankBookDTO bankBookDTO = new BankBookDTO();
		
		bankBookDTO.setBookNumber(100L);
		bankBookDTO.setBookName("book100");
		bankBookDTO.setBookRate(3.0);
		bankBookDTO.setBookSale(1);
		bankBookDTO.setBookDetail("book100detail");
		
		bankBookDTO = bankBookDAO.getBankBookDetail(bankBookDTO);
		assertNull(bankBookDTO);
	}
	
	@Test
	public void setBankBookAddTest() throws Exception{
		
		for(int i=1; i<=20; i++) {
		BankBookDTO bankBookDTO = new BankBookDTO();
			Random r = new Random();
			double d = r.nextDouble();
			int num = (int)(d*1000);
			d = num/100.0;
//			bankBookDTO.setBookNumber(100L);
			bankBookDTO.setBookName("KB Automatic"+i);
			bankBookDTO.setBookRate(d);
			bankBookDTO.setBookSale(1);
			bankBookDTO.setBookDetail("KB Auto"+i);
			int result = bankBookDAO.setBankBookAdd(bankBookDTO);
		}
//		assertNotEquals(0, result);
		System.out.println("Finish");
	}
	
	//@Test
	public void setBankBookDeleteTest() throws Exception{
		BankBookDTO bankBookDTO = new BankBookDTO();
		
		bankBookDTO.setBookNumber(100L);
		
		int result = bankBookDAO.setBankBookDelete(bankBookDTO);
		assertNotEquals(0, result);
		
	}
	
	
}
