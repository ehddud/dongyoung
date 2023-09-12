package homework.board.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import homework.T01Homework1JDBCUtil;
import homework.board.vo.T01Homework1BoardVO;

public class T01Homework1BoardDAO {

	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static T01Homework1BoardDAO boardDAO;
	private T01Homework1BoardDAO() {};
	public static T01Homework1BoardDAO getInstance() {
		if(boardDAO == null) boardDAO = new T01Homework1BoardDAO();
		return boardDAO;
	}
	
	public int insertPost(T01Homework1BoardVO bv) {
		
		int cnt = 0;
		
		try {
			conn = T01Homework1JDBCUtil.getConnection();
			String sql = "INSERT INTO JDBC_BOARD";
			sql += " (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT) ";
			sql += " VALUES (BOARD_SEQ.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getWriter());
			pstmt.setString(3, bv.getCont());
			
			cnt = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T01Homework1JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
			
	}
	
	public List<T01Homework1BoardVO> selectAll() {
		
		List<T01Homework1BoardVO> list = new ArrayList<T01Homework1BoardVO>();
		T01Homework1BoardVO vo = null;
		try {
			conn = T01Homework1JDBCUtil.getConnection();
			String sql = "SELECT * FROM JDBC_BOARD";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				vo = new T01Homework1BoardVO();
				vo.setNo(rs.getString("BOARD_NO"));
				vo.setTitle(rs.getString("BOARD_TITLE"));
				vo.setWriter(rs.getString("BOARD_WRITER"));
				vo.setCont(rs.getString("BOARD_CONTENT"));
				vo.setDate(rs.getDate("BOARD_DATE"));
				
				list.add(vo);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T01Homework1JDBCUtil.close(conn, stmt, pstmt, rs);
		}

		return list;
			
	}
	
	public int updatePost(int boardNo, T01Homework1BoardVO bv) {
		int result = 0;
		try {	
			conn = T01Homework1JDBCUtil.getConnection();
			
			String sql = "UPDATE JDBC_BOARD SET ";
			sql += " BOARD_TITLE = ? , BOARD_WRITER = ?, BOARD_CONTENT = ? ";
			sql += " WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getWriter());
			pstmt.setString(3, bv.getCont());
			pstmt.setInt(4, boardNo);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("변경 성공");
			}else {
				System.out.println("변경 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T01Homework1JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	
		return result;
	}
	
	public int deletePost(int boardNo) {
		
		int result = 0;
		
		try {
			
			conn = T01Homework1JDBCUtil.getConnection();
			String sql = "DELETE FROM JDBC_BOARD ";
			sql += " WHERE BOARD_NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T01Homework1JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return result;
	}
	
	public T01Homework1BoardVO selectOne(int boardNo) {
		T01Homework1BoardVO bv = null;
		try {
			conn = T01Homework1JDBCUtil.getConnection();
			String sql = "SELECT * FROM JDBC_BOARD WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();

			bv = new T01Homework1BoardVO();
			while(rs.next()) {
				bv.setNo(rs.getString("BOARD_NO"));
				bv.setTitle(rs.getString("BOARD_TITLE"));
				bv.setWriter(rs.getString("BOARD_WRITER"));
				bv.setDate(rs.getDate("BOARD_DATE"));
				bv.setCont(rs.getString("BOARD_CONTENT"));				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T01Homework1JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return bv;
	}
	
	
	public boolean isSelected(int boardNo) {
		
		try {
			
			conn = T01Homework1JDBCUtil.getConnection();
			
			String sql = "SELECT COUNT(*) FROM JDBC_BOARD WHERE BOARD_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) {
				
				cnt = rs.getInt(1); 
				
			}
			
			if(cnt > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T01Homework1JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return false;
		
		
	}
	
	
	
}
