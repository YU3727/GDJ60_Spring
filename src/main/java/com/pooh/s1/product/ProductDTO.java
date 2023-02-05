package com.pooh.s1.product;

public class ProductDTO {
//물건을 구성할 DTO 만듦
	
	private Long productNum;
	private String productName;
	private String productDetail;
	private Double productScore;
	
	
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
