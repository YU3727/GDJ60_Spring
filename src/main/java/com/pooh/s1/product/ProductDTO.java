package com.pooh.s1.product;

public class ProductDTO {
//230130 3교시 product 관련 기능 만들기
	
	//Null때문에 레퍼런스 타입으로 선언
	private Long productNum;
	private String productName;
	private String productDetail;
	private Double productScore;
	
	//기본생성자
	public ProductDTO() {
		
	}

	
	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public Double getProductScore() {
		return productScore;
	}

	public void setProductScore(Double productScore) {
		this.productScore = productScore;
	}
	
}
