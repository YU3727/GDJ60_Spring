package com.pooh.s1.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pooh.s1.util.DBConnection;

@Repository
public class MemberDAO {
//230202 8교시
//230206 7교시 Mybatis를 이용하여 memberJoin 만들어보기
//230214 2교시 회원가입할때 memberadd, memberroleadd를 동시에 호출하면 됨
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.pooh.s1.member.MemberDAO.";
	//어떤 table을 새로 만들거다 -> DAO, DTO는 새로 만들어야함
	//Mapper는 새로 만들건지 따로 만들건지 생각
	

	
	//setMemberUpdate
	public int setMemberUpdate(MemberDTO memberDTO) throws Exception{
		//UPDATE MEMBER SET MEMBERNAME = ?, MEMBERPHONE = ?, EMAIL=? WHERE ID = ?;
		return sqlSession.update(NAMESPACE+"setMemberUpdate", memberDTO);
	}
	
	
	//getMemberLogin
	public MemberDTO getMemberLogin(MemberDTO memberDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getMemberLogin", memberDTO);
	}
	
	//mybatis로 만들기
	public int setMemberAdd(MemberDTO memberDTO) throws Exception{
		//연결부는 database-context.xml에서 설정해줬음
		return sqlSession.insert(NAMESPACE+"setMemberAdd", memberDTO);	
	}
	
	public int setMemberRoleAdd(MemberDTO memberDTO) throws Exception{
		return sqlSession.insert(NAMESPACE+"setMemberRoleAdd", memberDTO);
	}
	
	
	//getMemberList
	public List<MemberDTO> getMemberList() throws Exception{
		//SELECT * FROM MEMBER
		return sqlSession.selectList(NAMESPACE+"getMemberList");
	}
	
//	//getMemberDetail
//	public MemberDTO getMemberDetail(MemberDTO memberDTO)throws Exception{
//		//SELECT * FROM MEMBER WHERE = ?
//		return sqlSession.selectOne(NAMESPACE+"getMemberDetail", memberDTO);
//	}
	
}
