package com.pooh.s1.bankbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pooh.s1.util.Pager;

@Service //객체만드는 Annotation
public class BankBookService {
//230208 2교시 Service - 나중에 작업
	
	@Autowired //의존성선언 + 주입 하는 Annotation
	private BankBookDAO bankBookDAO;
	
	//getBankBookList
	public List<BankBookDTO> getBankBookList() throws Exception{
		Pager pager = new Pager();
		long perPage = 10L; //한페이지에 나타낼 갯수 설정
		long page = 2;
		long s = page*perPage - (perPage-1);
		long l = page*perPage;
		
		pager.setStartRow(s);
		pager.setLastRow(l);
		List<BankBookDTO> ar = bankBookDAO.getBankBookList(pager);
		return ar;
	}
	
	//getBankBookDetail
	public BankBookDTO getBankBookDetail(BankBookDTO bankBookDTO) throws Exception{
		return bankBookDAO.getBankBookDetail(bankBookDTO);
	}
	
	//setBankBookAdd
	public int setBankBookAdd(BankBookDTO bankBookDTO) throws Exception{
		return bankBookDAO.setBankBookAdd(bankBookDTO);
	}
	
	//setBankBookUpdate
	public int setBankBookUpdate(BankBookDTO bankBookDTO) throws Exception{
		return bankBookDAO.setBankBookUpdate(bankBookDTO);
	}
	
	//setBankBookDelete
	public int setBankBookDelete(BankBookDTO bankBookDTO) throws Exception{
		return bankBookDAO.setBankBookDelete(bankBookDTO);
	}
	
}
