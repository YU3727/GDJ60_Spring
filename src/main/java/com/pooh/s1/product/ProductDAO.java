package com.pooh.s1.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pooh.s1.util.DBConnection;

@Repository
public class ProductDAO {
//DB랑 데이터를 주고받는 부분 DAO
	
	
	//getProductNum
	public Long getProductNum() throws Exception{
		
		Connection connection = DBConnection.getConnection();
		String sql = "SELECT PRODUCT_SEQ.NEXTVAL FROM DUAL";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		//rs에 들어있는 다음의 값을 불러들임
		rs.next();
		
		Long num = rs.getLong(1);
		
		DBConnection.disConnection(rs, st, connection);
		
		return num;
		
	}
	
	
	//getProductOptionList
	public List<ProductOptionDTO> getProductOptionList() throws Exception{
		List<ProductOptionDTO> productOptionDTOs = new ArrayList<ProductOptionDTO>();
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT * FROM PRODUCTOPTION";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		//rs값이 있을때까지 계속 실행해야함
		while(rs.next()) {
			ProductOptionDTO productOptionDTO = new ProductOptionDTO();
			productOptionDTO.setOptionNum(rs.getLong("OPTIONNUM"));
			productOptionDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productOptionDTO.setOptionName(rs.getString("OPTIONNAME"));
			productOptionDTO.setOptionPrice(rs.getInt("OPTIONPRICE"));
			productOptionDTO.setOptionStock(rs.getInt("OPTIONSTOCK"));	
		}
		
		DBConnection.disConnection(rs, st, connection);
		
		return productOptionDTOs;
		
	}
	
	
	//setAddProductOption
	public int setAddProductOption(ProductOptionDTO productOptionDTO) throws Exception{
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCTOPTION "
				+ "VALUES(PRODUCTOPTION_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		//외부의 옵션DTO에 들어있는 데이터를 통해 INSERT QUERY를 완성해야함. -> 매개변수 필요
		st.setLong(1, productOptionDTO.getProductNum());
		st.setString(2, productOptionDTO.getOptionName());
		st.setInt(3, productOptionDTO.getOptionPrice());
		st.setInt(4, productOptionDTO.getOptionStock());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnection(st, connection);
		
		return result;
	}
	
	
	//getProductDetail
	public ProductDTO getProductDetail(ProductDTO productDTO) throws Exception{
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCTNUM = ?";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setLong(1, productDTO.getProductNum());
		
		ResultSet rs = st.executeQuery();
		
		//rs값이 존재한다면 아래의 조건문 수행
		if(rs.next()) {
			productDTO = new ProductDTO();
			productDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productDTO.setProductName(rs.getString("PRODUCTNAME"));
			productDTO.setProductDetail(rs.getString("PRODUCTDETAIL"));
			productDTO.setProductScore(rs.getDouble("PRODUCTSCORE"));
		}else {
			productDTO = null;
		}
		
		DBConnection.disConnection(st, connection);
		
		return productDTO;
		
	}
	
	
	//getProductList
	public List<ProductDTO> getProductList() throws Exception{
		List<ProductDTO> ar = new ArrayList<ProductDTO>();
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCTNUM, PRODUCTNAME, PRODUCTSCORE " //PRODUCTDETAIL은 빼고
				+ "FROM PRODUCT ORDER BY PRODUCTSCORE DESC";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productDTO.setProductName(rs.getString("PRODUCTNAME"));
			productDTO.setProductScore(rs.getDouble("PRODUCTSCORE"));
			ar.add(productDTO);
		}
		
		DBConnection.disConnection(rs, st, connection);
		
		return ar;
	}
	
	
	//setAddProduct - getProductNum과 연계해서 사용
	public int setAddProduct(ProductDTO productDTO) throws Exception{
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCT "
				+ "VALUES(?, ?, ?, 0.0)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		//상품DTO에서 받아온 내용을 입력한다 -> 매개변수 필요
		st.setLong(1, productDTO.getProductNum());
		st.setString(2, productDTO.getProductName());
		st.setString(3, productDTO.getProductDetail());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnection(st, connection);
		
		return result;
	}
	
	
}
