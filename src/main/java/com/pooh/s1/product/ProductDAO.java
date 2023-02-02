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
//230130 3~5교시
//230201 6교시
	
	
	//5교시 getMax -> 6교시 getProductNum
	//제일 최근에 등록된 상품번호를 참조해서 옵션을 추가해주기 위한 메서드
	//옵션을 등록할때는 상품번호가 뭔지 알 수 없으므로 제일 최근에 등록된 상품(상품번호 시퀀스가 제일 높은값)을 가져옴
	public Long getProductNum() throws Exception{
		
		Connection con = DBConnection.getConnection();
		
//		String sql = "SELECT MAX(PRODUCTNUM) FROM PRODUCT";
		String sql = "SELECT PRODUCT_SEQ.NEXTVAL FROM DUAL";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		//무조건 1개의 응답은 온다(값이 없으면 0, 있으면 제일 높은 값)
		rs.next();
		
		Long num = rs.getLong(1); //1번 컬럼값을 가져오시오
		
		DBConnection.disConnection(rs, st, con);
		
		return num;
		
	}
	
	
	//옵션 전체 조회
	 public List<ProductOptionDTO> getProductOptionList () throws Exception {
	      List<ProductOptionDTO> poDTOs = new ArrayList<ProductOptionDTO>();
	      
	      Connection con = DBConnection.getConnection();
	      
	      String sql = "SELECT * FROM PRODUCTOPTION";
	      
	      PreparedStatement st = con.prepareStatement(sql);
	      
	      ResultSet rs = st.executeQuery();
	      
	      while(rs.next()) {
	         ProductOptionDTO poDTO = new ProductOptionDTO();
	         poDTO.setOptionNum(rs.getLong("OPTIONNUM"));
	         poDTO.setProductNum(rs.getLong("PRODUCTNUM"));
	         poDTO.setOptionName(rs.getString("OPTIONNAME"));
	         poDTO.setOptionPrice(rs.getInt("OPTIONPRICE"));
	         poDTO.setOptionStock(rs.getInt("OPTIONSTOCK"));
	      }
	      
	      DBConnection.disConnection(rs, st, con);
	      
	      return poDTOs;
	   }
	
	
	//옵션 인서트
	//옵션 dao안만들고 productdao에서
	public int setAddProductOption(ProductOptionDTO poDTO) throws Exception{
		
		Connection con = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCTOPTION"
				+ "VALUES(PRODUCTOPTION_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
//		st.setLong(1, poDTO.getOptionNum()); //받아온 데이터로 입력
		st.setLong(1, poDTO.getProductNum());
		st.setString(2, poDTO.getOptionName());
		st.setInt(3, poDTO.getOptionPrice());
		st.setInt(4, poDTO.getOptionStock());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnection(st, con);
		
		return result;
		
	}
	
	
	
	//getProductDetail
	public ProductDTO getProductDetail(ProductDTO pDTO) throws Exception{
		
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCTNUM = ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setLong(1, pDTO.getProductNum());
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			pDTO = new ProductDTO(); //pDTO를 새로 만들어서 넣어라
			pDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			pDTO.setProductName(rs.getString("PRODUCTNAME"));
			pDTO.setProductDetail(rs.getString("PRODUCTDETAIL"));
			pDTO.setProductScore(rs.getDouble("PRODUCTSCORE"));
		}else {
			pDTO = null;
		}
		
		DBConnection.disConnection(rs, st, con);
		
		return pDTO;
		
		
	}
	
	
	
	
	//제품조회기능
	//다형성 때문에 ArrayList 또한 List 타입이다.
	//혼자 개발하는게 아님 - 다른사람이 만든 table이 ArrayList라는 보장이 없기 때문에 부모형으로 리턴을 받아온다.
	//ex. LinkedList, ArrayList...
	public List<ProductDTO> getProductList() throws Exception{
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCTNUM, PRODUCTNAME, PRODUCTSCORE " //PRODUCTDETAIL은 일단 빼고
				+ "FROM PRODUCT "
				+ "ORDER BY PRODUCTSCORE DESC";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		//한줄 읽고 데이터 있으면 true, 없으면 false. 즉 데이터 없어질 때 까지 반복해라
		while(rs.next()) {
			ProductDTO pDTO = new ProductDTO();
			pDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			pDTO.setProductName(rs.getString("PRODUCTNAME"));
			pDTO.setProductScore(rs.getDouble("PRODUCTSCORE"));
			ar.add(pDTO);
		}
		
		DBConnection.disConnection(rs, st, con);
		
		return ar;
	}
	
	
	//setAddProduct
	public int setAddProduct(ProductDTO pDTO) throws Exception{
		
		Connection con = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCT "
				+ "VALUES(?, ?, ?, 0.0)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setLong(1, pDTO.getProductNum()); //시퀀스를 쓸 경우 쿼리문에 쓰지않고 productnum쪽에 써줌....
		st.setString(2, pDTO.getProductName());
		st.setString(3, pDTO.getProductDetail());
//		st.setDouble(4, pDTO.getProductScore()); //물건이 처음 등록될 때 평점은 0점이니까 시작점수를 0으로
		
		int result = st.executeUpdate();
		
		DBConnection.disConnection(st, con);
		
		return result;
		
	}
	
}
