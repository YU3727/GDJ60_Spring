package com.pooh.s1.board;

import java.util.List;

import com.pooh.s1.util.Pager;

public interface BbsDAO {

	//최상위 부모 DAO 인터페이스
	
	//totalCount - 페이징 처리를 위해 전체 글 개수를 리턴
	public Long getTotalCount(Pager pager) throws Exception;
	
	//list - 리턴의 generic에 모두 담을수 있는 type인 부모형으로 받는다
	public List<BbsDTO> getBoardList(Pager pager) throws Exception;
	
	//insert(add)
	public int setBoardAdd(BbsDTO bbsDTO) throws Exception;
	
	//update
	public int setBoardUpdate(BbsDTO bbsDTO) throws Exception;
	
	//delete
	public int setBoardDelete(BbsDTO bbsDTO) throws Exception;
}
