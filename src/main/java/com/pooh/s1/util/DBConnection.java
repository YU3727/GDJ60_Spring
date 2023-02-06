package com.pooh.s1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
//230130 1~2교시 JAVA-DB 연결
	
	//getConnection - 연결부
	public static Connection getConnection() throws Exception{
		//static - 그외지정자. 클래스에서 공통적으로 사용해라. 더이상 멤버메서드가 아니고 클래스(스태틱)메서드임
		//클래스메서드는 객체를 만들지 않고 사용 가능 사용 방법은 클래스명.메서드명
		
		//1.연결정보 나열 - id, pw, driver, url
		String user = "user01";
		String password = "user01";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
				//SID면 :sid이름, ServiceName이면 /ServiceName이름
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		//2.Driver를 메모리에 로딩하기
		Class.forName(driver); //문자열을 class로 만드는 작업
		
		//3.DB 연결하기
		return DriverManager.getConnection(url, user, password);
		
		//4.test 하기 - 메인메서드에
		
	}
	
	
	//disConnection - 연결해제부, SELECT문이랑 나머지랑 다르므로 오버로딩 이용
	public static void disConnection(ResultSet rs, PreparedStatement st, Connection connection) throws Exception{
		rs.close();
		st.close();
		connection.close();
	}
	
	public static void disConnection(PreparedStatement st, Connection connection) throws Exception{
		st.close();
		connection.close();
	}
	
}
