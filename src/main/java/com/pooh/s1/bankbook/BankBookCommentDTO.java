package com.pooh.s1.bankbook;

import com.pooh.s1.board.BbsDTO;

public class BankBookCommentDTO extends BbsDTO{
	
	private Long bookNumber;

	public BankBookCommentDTO() {
		
	}
	
	public Long getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(Long bookNumber) {
		this.bookNumber = bookNumber;
	}
	
	
}
