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
	
	public int memberJoin(MemberDTO memberDTO) throws Exception{
		
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
}
