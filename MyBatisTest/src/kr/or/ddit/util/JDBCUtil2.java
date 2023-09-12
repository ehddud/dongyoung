package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * db.properties 파일의 내용으로 DB정보를 설정하는 방법
 * 방법1 => Properties 객체 이용하기
 */
public class JDBCUtil2 {

	static Properties prop;   // 객체 변수 선언

	static {
		
		prop = new Properties();		
		
		try {
			// Properties 객체로 파일내용 읽기
			prop.load(new FileInputStream("./res/db.properties"));
			
			// 드라이버 로딩 확인
			Class.forName(prop.getProperty("driver"));
			System.out.println("드라이버 로딩 완료");
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Connection 객체 가져오기
	 * @return Connection 객체, 예외발생시 null 리턴함
	 */
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(
						prop.getProperty("url"),
						prop.getProperty("username"),
						prop.getProperty("password")
					);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 자원 반납 메서드
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, 
							Statement stmt, 
							PreparedStatement pstmt, 
							ResultSet rs) {
		
		if(rs != null)try {rs.close();}catch(SQLException e) {}
		if(stmt != null)try {stmt.close();}catch(SQLException e) {}
		if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
		if(conn != null)try {conn.close();}catch(SQLException e) {}
		
	}
	
}
