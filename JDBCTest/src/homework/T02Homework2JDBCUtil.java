package homework;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class T02Homework2JDBCUtil {

	/*
	 * static block : 클래스 static 멤버의 초기화에 사용
	 * 클래스가 처음 로딩될 때 한번만 수행
	 * 클래스 정보에 접근해서 불러들일 때
	 */
	static Properties prop;
	static {
		
		prop = new Properties();
		try {
			prop.load(new FileInputStream("res/db.properties"));
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// Connection 객체 가져오기
	// DB에 연결
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password")
					);
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	// 자원 반납 메서드 (close())
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		if(rs != null)try {rs.close();} catch (SQLException e) {}
		if(pstmt != null)try {pstmt.close();} catch (SQLException e) {}
		if(stmt != null)try {stmt.close();} catch (SQLException e) {}
		if(conn != null)try {conn.close();} catch (SQLException e) {}
		
	}
	
}
