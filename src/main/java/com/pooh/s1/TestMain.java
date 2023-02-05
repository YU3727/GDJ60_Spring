package com.pooh.s1;

import java.sql.Connection;

import com.pooh.s1.util.DBConnection;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(connection != null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
