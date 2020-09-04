package com.test.board;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private Connection conn = null;
	
	public Connection open() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "board";
		String pw = "java1234";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw);
			return conn;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public void close() {
		try {
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Connection open(String host, String id, String pw) {
		String url = "jdbc:oracle:thin:@"+host+":1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw);
			return conn;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}
