package com.pooh.s1.board.notice;

import java.util.List;

import com.pooh.s1.board.BoardDTO;
import com.pooh.s1.board.BoardFileDTO;

public class NoticeDTO extends BoardDTO{
	
	//파일 연결을 위해 멤버변수 선언(notice는 파일들을 가지고있다, 1:n) -> mapper 수정
	private List<BoardFileDTO> boardFileDTOs;
	
	public NoticeDTO() {
		
	}

	public List<BoardFileDTO> getBoardFileDTOs() {
		return boardFileDTOs;
	}

	public void setBoardFileDTOs(List<BoardFileDTO> boardFileDTOs) {
		this.boardFileDTOs = boardFileDTOs;
	}
	
}
