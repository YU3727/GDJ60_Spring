package com.pooh.s1.bankbook;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pooh.s1.board.BbsDAO;
import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.board.BbsService;
import com.pooh.s1.util.Pager;

@Service
public class BankBookCommentService implements BbsService{

	//Source - ovveride/implements method... 선택
	
	//private BankBookCommentDAO bankBookCommentDAO;
	
	//이것도 가능. autowired는 데이터타입이 일치하는것 먼저 찾고, 없으면 bean의 이름(여기서는 bankBookCommentDAO)이 일치하는것을 차선으로 찾음
	//이것의 장점은 다른 타입(같은 BbsDAO를 상속받은)으로 갈아 끼우기가 쉽다
	@Autowired
	private BankBookCommentDAO bankBookCommentDAO;
	
	@Override
	public List<BbsDTO> getBoardList(Pager pager) throws Exception {
		pager.makeRow();
		
		Long count = bankBookCommentDAO.getTotalCount(pager);
		pager.makeNum(count);
		
		return bankBookCommentDAO.getBoardList(pager);
	}

	@Override
	public int setBoardAdd(BbsDTO bbsDTO, MultipartFile[] multipartFiles, HttpSession session) throws Exception {
		return bankBookCommentDAO.setBoardAdd(bbsDTO);
	}

	@Override
	public int setBoardUpdate(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return bankBookCommentDAO.setBoardUpdate(bbsDTO);
	}

	@Override
	public int setBoardDelete(BbsDTO bbsDTO, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		//session은 파일 지울때 쓰던거
		return bankBookCommentDAO.setBoardDelete(bbsDTO);
	}
	
}
