package com.pooh.s1.bankbook;

public class BankBookImgDTO {

	//table의 column명과 동일하게
	private Long fileNum;
	private Long bookNumber;
	private String fileName;
	private String oriName;
	
	public BankBookImgDTO() {
		
	}

	public Long getFileNum() {
		return fileNum;
	}

	public void setFileNum(Long fileNum) {
		this.fileNum = fileNum;
	}

	public Long getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(Long bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriName() {
		return oriName;
	}

	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	
	
	
}
