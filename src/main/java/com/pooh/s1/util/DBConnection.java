package com.pooh.s1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
//db와 연결/해제를 담당하는 부분
	
	//getConnection - 연결부
	//객체생성 없이 사용하길 원하므로 static으로 선언
	public static Connection getConnection() throws Exception{
		
		//1. 연결정보 전송(id, pw, url, driver)
		//2. Driver를 메모리에 로딩
		//3. DB 연결하기
		//4. test는 메인메서드에
		
		String user = "user01";
		String password = "user01";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		
		return DriverManager.getConnection(url, user, password);
	}
	
	
	//disConnection - 해제부
	public static void disConnection(ResultSet rs, PreparedStatement st, Connection connection) throws Exception{
		rs.close();
		st.close();
		connection.close();
	}
	
	//select문을 제외한 나머지 부분 오버로딩
	public static void disConnection(PreparedStatement st, Connection connection) throws Exception{
		st.close();
		connection.close();
	}
}
