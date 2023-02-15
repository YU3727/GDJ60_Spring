package com.pooh.s1.bankbook;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pooh.s1.util.Pager;

@Repository
public class BankBookDAO {
//230207 7교시 DAO 직접 만들기. DTO, DAO만 만듦.
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.pooh.s1.bankbook.BankBookDAO.";
	
	//메서드를 만들어야한다.
	//리턴을 뭘할건지, 매개변수 받는게 있어야하나없어도되나 다 생각해보고 해야함
	//머리속으로 쿼리문을 생각해봐야함. 결과가 뭐가오는지, 필요한게 뭔지
	
	//getBankBookCount
	//게시글이 몇개인지 세는 메서드
	public Long getBankBookCount() throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getBankBookCount");
	}
	
	//getBankBookList
	public List<BankBookDTO> getBankBookList(Pager pager) throws Exception{
		//SELECT * FROM BANKBOOK
		return sqlSession.selectList(NAMESPACE+"getBankBookList", pager);
	}//namespace : 어느 맵퍼냐, + id : 어느 메서드(맵퍼의id)
	
	//getBankBookDetail
	public BankBookDTO getBankBookDetail(BankBookDTO bankBookDTO) throws Exception{
		//SELECT * FROM BANKBOOK WHERE BOOKNUMBER = #{}
		//어떤 bankbook 정보를 알고싶은지 특정지어야 하기 때문에 매개변수로 알고싶은 DTO(bookNum)를 넣어줘야한다
		return sqlSession.selectOne(NAMESPACE+"getBankBookDetail", bankBookDTO);
	}   //namespace+id까지는 mapper의 주소, 뒤에는 매개변수는 mapper로 보내줄 정보, DTO
	
	//setBankBookAdd
	public int setBankBookAdd(BankBookDTO bankBookDTO) throws Exception{
		//INSERT INTO BANKBOOK(BOOKNUMBER, BOOKNAME, BOOKRATE, BOOKSALE, BOOKDETAIL) VALUES(?, ?, ?, ?, ?)
		//어떤 bankbook 정보를 넣을건지 특정지어야 하기 때문에 매개변수로 입력할 bankbook 정보를 담은 DTO를 넣어준다
		return sqlSession.insert(NAMESPACE+"setBankBookAdd", bankBookDTO);
	}
	
	//setBankBookUpdate
	public int setBankBookUpdate(BankBookDTO bankBookDTO) throws Exception{
		//일단 이거는 빼고(230208 8교시)
		//UPDATE BANKBOOK SET BOOKNAME = ?, BOOKRATE = ?, BOOKSALE = ?, BOOKDETAIL = ? WHERE BOOKNUMBER = ?
		//sqlSession : DB 연결하는것,(mapper의 id가 일치하는것을 호출하자, 필요한DataType)
		return sqlSession.update(NAMESPACE+"setBankBookUpdate", bankBookDTO);
	}
	
	//setBankBookDelete
	public int setBankBookDelete(BankBookDTO bankBookDTO) throws Exception{
		//DELETE BANKBOOK WHERE BOOKNUMBER = ?
		return sqlSession.delete(NAMESPACE+"setBankBookDelete", bankBookDTO);
	}
	
}
