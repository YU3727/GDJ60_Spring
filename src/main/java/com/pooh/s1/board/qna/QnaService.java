package com.pooh.s1.board.qna;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pooh.s1.board.BbsDAO;
import com.pooh.s1.board.BbsDTO;
import com.pooh.s1.board.BoardDAO;
import com.pooh.s1.board.BoardDTO;
import com.pooh.s1.board.BoardFileDTO;
import com.pooh.s1.board.BoardService;
import com.pooh.s1.util.FileManager;
import com.pooh.s1.util.Pager;

@Service
public class QnaService implements BoardService{
	
	@Autowired
	private QnaDAO qnaDAO;
//	private BoardDAO qnaDAO; 얘도 가능
	@Autowired
	private FileManager fileManager;

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
	public int setBoardAdd(BbsDTO bbsDTO, MultipartFile [] multipartFiles, HttpSession session) throws Exception {
		//글쓴 것 insert와 파일 insert중에 뭘 먼저해야하는가? -> erdCloud에 가서 보면 글이 있어야 파일이 있을 수 있음(FK 관계에 의해 - 데이터 무결성)
		//1. 글 insert
		int result = qnaDAO.setBoardAdd(bbsDTO);
		System.out.println("FILE SIZE " + multipartFiles.length);
		//1. file HDD에 저장
		File file = new File("/upload/qna");
		//파일 객체가 주는 주소 확인용
		System.out.println("path : "+file.getPath());
		System.out.println("realpath :"+file.getAbsolutePath());
		
		String realPath = session.getServletContext().getRealPath("/resources/upload/qna");
		System.out.println(realPath);
		
		//받아온게 파일 배열이니까 저장작업을 반복해야한다
		for(MultipartFile multipartFile : multipartFiles) {
			//파일 사이즈가 0이면 또는 비어있는지를 확인함
			if(multipartFile.isEmpty()) {
				continue; //조건식으로 올라가라
			}
			String fileName = fileManager.fileSave(multipartFile, realPath);
			
			//DAO, MAPPER 다 만들었으니 Service에서 호출하면 된다.
			//2. DB INSERT
			//객체만 만들면 기본값(0, null)이 들어가있음. mapper로 받아온 데이터를 넣으려고 하는거지 자동으로 들어가지 않음. 데이터 삽입은 아래에서.
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			//실제 입력이 필요한 데이터는 Mapper에서 볼 수 있다. 여기서는 NUM, FILENAME, ORINAME 세가지 이걸 Service에서 입력해준다.
			boardFileDTO.setNum(bbsDTO.getNum());
			boardFileDTO.setFileName(fileName);
			boardFileDTO.setOriName(multipartFile.getOriginalFilename());
			
			result = qnaDAO.setBoardFileAdd(boardFileDTO);
		}
		
		return result;
	}

	@Override
	public int setBoardUpdate(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setBoardDelete(BbsDTO bbsDTO, HttpSession session) throws Exception {
		//나중에 비밀번호 확인작업같은거 하면 여기서
		
		//CASCADE 옵션이 있으면, 글에 묶인 파일을 먼저 조회해와야 한다.(대신 따로 자식파일을 지울 필요는 없음)
		List<BoardFileDTO> ar = qnaDAO.getBoardFileList(bbsDTO);
		int result = qnaDAO.setBoardDelete(bbsDTO);
		
		//파일명 조회를 위해 mapper에 쿼리를 만들어야한다 - getBoardFileList
		if(result > 0) {
			//저장할때도 os랑 얘기해서 직접 하고, 지울때도 마찬가지.
			//HDD에 있는 파일을 지우기 위해서는, 경로와 파일명이 필요하다. 즉, session이 필요하다
			String realPath = session.getServletContext().getRealPath("resources/upload/qna");
			
			for(BoardFileDTO boardFileDTO : ar) {
				boolean check = fileManager.fileDelete(realPath, boardFileDTO.getFileName());	
			}
		}
		return result;
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
	
	
	@Override
	public BoardFileDTO getBoardFileDetail(BoardFileDTO boardFileDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getBoardFileDetail(boardFileDTO);
	}
	
}
