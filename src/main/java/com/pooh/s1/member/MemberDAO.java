package com.pooh.s1.member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pooh.s1.util.DBConnection;

@Repository
public class MemberDAO {
//230202 8교시
//230206 7교시 Mybatis를 이용하여 memberJoin 만들어보기	
	
	
	//mybatis로 만들기
	public int memberJoin(MemberDTO memberDTO) throws Exception{
		//어떤 table을 새로 만들거다 -> DAO, DTO는 새로 만들어야함
		//Mapper는 새로 만들건지 따로 만들건지 생각
		
		//연결부는 database-context.xml에서 설정해줬음
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO MEMBER "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setString(1, memberDTO.getID());
		st.setString(2, memberDTO.getPW());
		st.setString(3, memberDTO.getMemberName());
		st.setString(4, memberDTO.getMemberPhone());
		st.setString(5, memberDTO.getEmail());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnection(st, connection);
	
		return result;	
}
	
	
	//memberJoin
//	public int memberJoin(MemberDTO memberDTO) throws Exception{
//		
//		Connection connection = DBConnection.getConnection();
//		
//		String sql = "INSERT INTO MEMBER "
//				+ "VALUES(?, ?, ?, ?, ?)";
//		
//		PreparedStatement st = connection.prepareStatement(sql);
//		
//		st.setString(1, memberDTO.getID());
//		st.setString(2, memberDTO.getPW());
//		st.setString(3, memberDTO.getMemberName());
//		st.setString(4, memberDTO.getMemberPhone());
//		st.setString(5, memberDTO.getEmail());
//		
//		int result = st.executeUpdate();
//		
//		DBConnection.disConnection(st, connection);
//		
//		return result;	
//	}
}
