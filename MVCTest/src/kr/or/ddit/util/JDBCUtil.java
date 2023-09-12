package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	// 클래스 초기화 블럭 : 클래스 변수의 복잡한 초기화에 사용된다. 
	// 클래스가 처음 로딩될 때 한번만 수행된다. 
	// 클래스 정보에 접근해서 불러들일 때
	// 처음 한번만 호출
	static {
		try {
			// 드라이버 로딩 확인
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 완료");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Connection 객체 가져오기
	 * @return Connection 객체, 예외발생시 null 리턴함
	 */
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"PC06", "java" );
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
