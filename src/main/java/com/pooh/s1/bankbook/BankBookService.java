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
	public List<BankBookDTO> getBankBookList(Pager pager) throws Exception{
		
		
		//List에서 게시글을 페이징처리 하기때문에 따로 메서드를 만들지 않고 여기서 처리한다
		Long totalCount = bankBookDAO.getBankBookCount(pager);
		
		//이렇게 써도 controller로 count값이 간다 왜? java라서 그렇다.
		//pager.setTotalCount(totalCount);
		pager.makeNum(totalCount);
		//계산작업을 더이상 하지않고, Pager 클래스에서 만들어둔 계산메서드를 호출한다
		pager.makeRow();
		
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
