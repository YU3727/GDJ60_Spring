package com.pooh.s1.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pooh.s1.util.DBConnection;

@Repository
public class ProductDAO {
//230130 3~5교시
//230201 6교시
//230206 2교시 삭제하는 메서드, 3~4교시 XML(Mapper, database-content) 연결
	
	//database-context.xml에서 만든 객체의 의존성을 표시해줌
	@Autowired
	private SqlSession sqlSession; //맵퍼의 위치를 찾는것
	//상수의 변수명을 사용하고싶다. 여기에는 파일명을 쓰는게 아니라, mapper의 속성을 써줘야한다.(일치시키면 됨)
	//어느 맵퍼를 쓸건지는 이 이름으로 찾아간다. 중요한 정보
	private final String NAMESPACE = "com.pooh.s1.product.productDAO."; //이 맵퍼들의 위치에서 어느 맵퍼를 쓸건지 결정
	
	//순서
	//sqlSession을 따라가서 namespace를 보고 주소를 찾아갈거다.
	
	
	
	//230206, MyBatis를 이용하여 다듬은 DAO 메서드들
	
	
	
	//setProductDelete
	public int setProductDelete(Long productNum) throws Exception{
		
		return sqlSession.delete(NAMESPACE+"setProductDetail", productNum);
	}
	
	//getProductDetail
	public ProductDTO getProductDetail(ProductDTO pDTO) throws Exception{
		//
		return sqlSession.selectOne(NAMESPACE+"getProductDetail", pDTO);	
	}
	
	//getProductList
	public List<ProductDTO> getProductList() throws Exception{

		return sqlSession.selectList(NAMESPACE+"getProductList");
	}
	
	//setAddProduct
	public int setAddProduct(ProductDTO pDTO) throws Exception{		
		//어느Mapper의 어느id를 가져올거냐?
		//Mapper의 위치는 NAMESPACE로, ID는 맵퍼내의 id = 해당메서드명으로 설정했기때문에 그대로 써주면 됨
		return sqlSession.insert(NAMESPACE+"setAddProduct");
	}
	
	
	
	//230206 2교시 삭제하는 메서드
//	public int setProductDelete(Long productNum) throws Exception{
//		int result = 0;
//		
//		//1.DB 연결
//		Connection connection = DBConnection.getConnection();
//		
//		//2.SQL 연결
//		String sql = "DELETE PRODUCT WHERE PRODUCTNUM = ?";
//		
//		//3.미리보내기
//		PreparedStatement st = connection.prepareStatement(sql);
//		
//		//4.?세팅
//		//원래는 DTO로 받는게 좋은데, 수업특성상 Long으로 받아본다.
//		st.setLong(1, productNum);
//		
//		//5.?값 최종적으로 보내기, 결과처리
//		result = st.executeUpdate();
//		
//		//6.DB 연결해제, 리턴값 보내기
//		DBConnection.disConnection(st, connection);
//		
//		return result;
//	}
	
	
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
//	public ProductDTO getProductDetail(ProductDTO pDTO) throws Exception{
//		
//		
//		Connection con = DBConnection.getConnection();
//		
//		//쿼리문작성, 미리보내기는 mapper(mybatis)가 해줌
//		String sql = "SELECT * FROM PRODUCT WHERE PRODUCTNUM = ?";
//		PreparedStatement st = con.prepareStatement(sql);
//		
//		//Mapper에서 #{}로 처리함
//		st.setLong(1, pDTO.getProductNum());
//		
//		//mapper(mybatis)가 해줌
//		ResultSet rs = st.executeQuery();
//		
//		if(rs.next()) {
//			pDTO = new ProductDTO(); //pDTO를 새로 만들어서 넣어라
//			pDTO.setProductNum(rs.getLong("PRODUCTNUM"));
//			pDTO.setProductName(rs.getString("PRODUCTNAME"));
//			pDTO.setProductDetail(rs.getString("PRODUCTDETAIL"));
//			pDTO.setProductScore(rs.getDouble("PRODUCTSCORE"));
//		}else {
//			pDTO = null;
//		}
//		
//		//mapper(mybatis)가 해줌
//		DBConnection.disConnection(rs, st, con);
//		
//		return pDTO; 이거 대신에 아래꺼 사용
//		//namespace + id이름인데 id이름은 곧 메서드명이다 // 뒤에 보내주는 매개변수는 mapper에 있는 parameterType, resultType과 일치해야한다.
//		//결과값이 0 또는 하나로 보장이 되는 경우는 selectOne으로 받는다. 두개이상 나오면 에러
//		return sqlSession.selectOne(NAMESPACE+"getProductDetail", pDTO);
//		
//	}
	
	
	//제품조회기능
	//다형성 때문에 ArrayList 또한 List 타입이다.
	//혼자 개발하는게 아님 - 다른사람이 만든 table이 ArrayList라는 보장이 없기 때문에 부모형으로 리턴을 받아온다.
	//ex. LinkedList, ArrayList...
//	public List<ProductDTO> getProductList() throws Exception{
//		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
//		
//		//
//		Connection con = dataSource.getConnection();
//		
//		//쿼리문작성, 미리보내기는 mapper(mybatis)가 해줌
//		String sql = "SELECT PRODUCTNUM, PRODUCTNAME, PRODUCTSCORE " //PRODUCTDETAIL은 일단 빼고
//				+ "FROM PRODUCT "
//				+ "ORDER BY PRODUCTSCORE DESC";
//		PreparedStatement st = con.prepareStatement(sql);
//		
//		//mapper(mybatis)가 해줌
//		ResultSet rs = st.executeQuery();
//		
//		//한줄 읽고 데이터 있으면 true, 없으면 false. 즉 데이터 없어질 때 까지 반복해라
//		while(rs.next()) {
//			ProductDTO pDTO = new ProductDTO();
//			pDTO.setProductNum(rs.getLong("PRODUCTNUM"));
//			pDTO.setProductName(rs.getString("PRODUCTNAME"));
//			pDTO.setProductScore(rs.getDouble("PRODUCTSCORE"));
//			ar.add(pDTO);
//		}
//		
//		//mybatis가 해줌
//		DBConnection.disConnection(rs, st, con);
//		
//		return ar;
//		//매개변수가 없기 때문에 안보내준다.
//		return sqlSession.selectList(NAMESPACE+"getProductList");
//	}
	
	
	//setAddProduct
//	public int setAddProduct(ProductDTO pDTO) throws Exception{
//		
//		Connection con = DBConnection.getConnection();
//		
//		String sql = "INSERT INTO PRODUCT "
//				+ "VALUES(?, ?, ?, 0.0)";
//		
//		PreparedStatement st = con.prepareStatement(sql);
//		
//		st.setLong(1, pDTO.getProductNum()); //시퀀스를 쓸 경우 쿼리문에 쓰지않고 productnum쪽에 써줌....
//		st.setString(2, pDTO.getProductName());
//		st.setString(3, pDTO.getProductDetail());
////		st.setDouble(4, pDTO.getProductScore()); //물건이 처음 등록될 때 평점은 0점이니까 시작점수를 0으로
//		
//		int result = st.executeUpdate();
//		
//		DBConnection.disConnection(st, con);
//		
//		return result;
//		
//	}
	
}
