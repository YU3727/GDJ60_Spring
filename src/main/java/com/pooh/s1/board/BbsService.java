package com.pooh.s1.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.pooh.s1.util.Pager;

public interface BbsService {

	//최상위 부모 Service
	
	//list - 모든 DTO를 다 담을 수 있는 형으로 선언
	public List<BbsDTO> getBoardList(Pager pager) throws Exception;
	
	//insert
	//파일 추가를 위해 MultipartFile, HttpSession추가.(servlet context 꺼내려고)
	//service까지는 매개변수명 동일하게, 컨트롤러에서는 jsp명의 속성명 name과 일치
	public int setBoardAdd(BbsDTO bbsDTO, MultipartFile [] multipartFiles, HttpSession session) throws Exception;
	
	//update
	public int setBoardUpdate(BbsDTO bbsDTO) throws Exception;
	
	//delete
	public int setBoardDelete(BbsDTO bbsDTO, HttpSession session) throws Exception;
}
