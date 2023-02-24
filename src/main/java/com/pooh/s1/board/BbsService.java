package com.pooh.s1.board;

import java.util.List;

import com.pooh.s1.util.Pager;

public interface BbsService {

	//최상위 부모 Service
	
	//list - 모든 DTO를 다 담을 수 있는 형으로 선언
	public List<BbsDTO> getBoardList(Pager pager) throws Exception;
	
	//insert
	public int setBoardAdd(BbsDTO bbsDTO) throws Exception;
	
	//update
	public int setBoardUpdate(BbsDTO bbsDTO) throws Exception;
	
	//delete
	public int setBoardDelete(BbsDTO bbsDTO) throws Exception;
}
