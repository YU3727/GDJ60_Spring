package com.pooh.s1.board;

public class BoardFileDTO {
//DTO를 새로 만들면 mybatisConfig에 Alias를 등록하러가자
	
	private Long fileNum;
	private Long num;
	private String fileName;
	private String oriName;
	
	
	
	public Long getFileNum() {
		return fileNum;
	}
	public void setFileNum(Long fileNum) {
		this.fileNum = fileNum;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
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
