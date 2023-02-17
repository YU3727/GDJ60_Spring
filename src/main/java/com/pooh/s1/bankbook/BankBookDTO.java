package com.pooh.s1.bankbook;

public class BankBookDTO {
//230207 7교시
	
	private Long bookNumber;
	private String bookName;
	private Double bookRate;
	private Integer bookSale;
	private String bookDetail;
	private BankBookImgDTO bankBookImgDTO;
	
	public BankBookDTO() {
		
	}

	
	public BankBookImgDTO getBankBookImgDTO() {
		return bankBookImgDTO;
	}


	public void setBankBookImgDTO(BankBookImgDTO bankBookImgDTO) {
		this.bankBookImgDTO = bankBookImgDTO;
	}


	public Long getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(Long bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Double getBookRate() {
		return bookRate;
	}

	public void setBookRate(Double bookRate) {
		this.bookRate = bookRate;
	}

	public Integer getBookSale() {
		if(this.bookSale == null || this.bookSale != 1) {
			this.bookSale = 0;
		}
		return bookSale;
	}

	public void setBookSale(Integer bookSale) {
//		add page에서 switch로 데이터를 입력하는경우 null이나 1이 넘어오기떄문에 getter/setter에 안전장치를 마련해둔다
		if(bookSale == null || bookSale != 1) {
			this.bookSale = 0;
		}else {
			this.bookSale = bookSale;	
		}
	}

	public String getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(String bookDetail) {
		this.bookDetail = bookDetail;
	}
	
	
}
