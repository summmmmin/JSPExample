package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;

// Connection 객체 반환
public class DAO {
	public static Connection getConnect() {
		
		// jdbc 연결 접속 조회
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
