package com.pooh.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pooh.s1.board.BbsDAO;
import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.board.BoardDAO;
import com.pooh.s1.board.BoardDTO;
import com.pooh.s1.board.BoardService;
import com.pooh.s1.util.Pager;

@Service
public class QnaService implements BoardService{
	
	@Autowired
	private QnaDAO qnaDAO;
//	private BoardDAO qnaDAO; 얘도 가능

	@Override
	public List<BbsDTO> getBoardList(Pager pager) throws Exception {
		//DB에서 몇개 꺼내올지 계산
		pager.makeRow();
		//페이징 계산을 위해 토탈카운트 계산
		Long count = qnaDAO.getTotalCount(pager);
		//실제 페이징 계산
		pager.makeNum(count);
		//리스트 받아오기
		return qnaDAO.getBoardList(pager);
	}

	@Override
	public int setBoardAdd(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.setBoardAdd(bbsDTO);
	}

	@Override
	public int setBoardUpdate(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setBoardDelete(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO getBoardDetail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getBoardDetail(boardDTO);
	}
	
	//상속받고, 자신의 클래스에서만 사용하는것은 따로 만들어주면 된다
	//reply
	public int setReplyAdd(QnaDTO qnaDTO) throws Exception{
		//qnaDTO에 들어가있는 것
		//num : 부모의 글번호
		//writer, title, contents : 답글로 입력한 값
		//ref : null
		//step : null
		//depth : null
		//답글기능을 이용하기 위해서는 부모의 num으로 부모의 ref, step, depth를 먼저 조회해와야한다
		
		//1. 부모의 정보를 조회해온다
		QnaDTO parent = (QnaDTO)qnaDAO.getBoardDetail(qnaDTO);
		
		//2. 조회해온 부모의 정보로부터 답글의 ref, step, depth를 세팅
		//qnaDTO에는 답글을 insert 하는 정보, parent에는 부모글의 정보
		//ref : 부모의 ref(부모글, 답글을 그룹으로 묶는 용도)
		qnaDTO.setRef(parent.getRef());
		//step : 부모의 step +1(그룹내에서 순서를 설정)
		qnaDTO.setStep(parent.getStep()+1);
		//depth : 부모의 depth +1(들여쓰기 용도)
		qnaDTO.setDepth(parent.getDepth()+1);
		
		//3. Step을 Update
		//쿼리문 만들고... 부모의 정보로 자식의 정보를 업데이트 해야한다!!
		int result = qnaDAO.setStepUpdate(parent);
		
		//4. 답글을 insert
		//부모글 insert만 있고 답글 insert가 없음. -> 쿼리문 만들러 감
		//이제 받아온 자식인 답글을 넣으면 됨
		result = qnaDAO.setReplyAdd(qnaDTO);
		
		return result;
	}
	
}
