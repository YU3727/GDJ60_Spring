package com.pooh.s1.board;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public interface BoardService extends BbsService{

	//Detail
	//글번호 받아야하는데 글번호는 boardDTO에 있음
	public BoardDTO getBoardDetail(BoardDTO boardDTO) throws Exception;
	
	//메서드 오버로딩을 통해 파일들을 넣고싶은 경우에는 얘를 쓰면 된다. -> 그러나 BbsService에서...
//	public int setBoardAdd(BbsDTO bbsDTO, MultipartFile [] multipartFiles) throws Exception; //얘를 쓰면 두번 받아야함...
//	public int setBoardAdd(BoardDTO boardDTO, MultipartFile [] files) throws Exception; //얘도 가능
	
	public BoardFileDTO getBoardFileDetail(BoardFileDTO boardFileDTO) throws Exception;
	
	//update는 오버로딩
	public int setBoardUpdate(BbsDTO bbsDTO, MultipartFile [] multipartFiles, HttpSession session, Long [] fileNums) throws Exception;
	
	
}
