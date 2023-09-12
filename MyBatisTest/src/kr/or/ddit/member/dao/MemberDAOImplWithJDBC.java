package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDAOImplWithJDBC implements IMemberDAO{

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IMemberDAO memDAO;
	
	private MemberDAOImplWithJDBC() {};
	
	public static IMemberDAO getInstance() {
		if(memDAO == null) {
			memDAO = new MemberDAOImplWithJDBC();
		}
		
		return memDAO;
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {

			// getConnection(url, user, pw);
			conn = JDBCUtil3.getConnection();
			String sql = "INSERT INTO MYMEMBER ";
			sql += " (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) ";
			sql += " VALUES (? ,? , ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate();
			
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
		
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "UPDATE MYMEMBER " 
					+ " SET MEM_NAME = ?, "  
					+ " MEM_TEL = ?, " 
					+ " MEM_ADDR = ? " 
					+ " WHERE MEM_ID= ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
		
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;

		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "SELECT COUNT(*) AS CNT ";
			sql += " FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) {
				cnt = rs.getInt("CNT");
			}
			
			if(cnt > 0) {
				isExist = true;
			}
			
		} catch (SQLException e) {
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return isExist;

		
	}

	@Override
	public List<MemberVO> selectAll() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM MYMEMBER";
			
			rs = stmt.executeQuery(sql);
		
			while(rs.next()) {
				String memId = rs.getString("MEM_ID");
				Date regDt = rs.getTimestamp("REG_DT");
				String memName = rs.getString("MEM_NAME");
				String memTel = rs.getString("MEM_TEL");
				String memAddr = rs.getString("MEM_ADDR");

				MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
				mv.setRegDt(regDt);
				
				memList.add(mv);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
		

	}

	@Override
	public List<MemberVO> searchMember(MemberVO paramMv) {
		

		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			/////////////////////동적 쿼리////////////////////////////////
			
			String sql = "SELECT * FROM MYMEMBER WHERE 1=1 ";
			if(paramMv.getMemId() != null && !paramMv.getMemId().equals("")) {
				sql += " AND MEM_ID = ? ";
			}
			if(paramMv.getMemName() != null && !paramMv.getMemName().equals("")) {
				sql += " AND MEM_NAME = ? ";
			}
			if(paramMv.getMemTel() != null && !paramMv.getMemTel().equals("")) {
				sql += " AND MEM_TEL = ? ";
			}
			if(paramMv.getMemAddr() != null && !paramMv.getMemAddr().equals("")) {
				sql += " AND MEM_ADDR LIKE '%' || ? || '%' ";
			}
			
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(paramMv.getMemId() != null && !paramMv.getMemId().equals("")) {
				pstmt.setString(index++, paramMv.getMemId());
			}
			if(paramMv.getMemName() != null && !paramMv.getMemName().equals("")) {
				pstmt.setString(index++, paramMv.getMemName());
			}
			if(paramMv.getMemTel() != null && !paramMv.getMemTel().equals("")) {
				pstmt.setString(index++, paramMv.getMemTel());
			}
			if(paramMv.getMemAddr() != null && !paramMv.getMemAddr().equals("")) {
				pstmt.setString(index++, paramMv.getMemAddr());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String memId = rs.getString("MEM_ID");
				Date regDt = rs.getTimestamp("REG_DT");
				String memName = rs.getString("MEM_NAME");
				String memTel = rs.getString("MEM_TEL");
				String memAddr = rs.getString("MEM_ADDR");

				MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
				mv.setRegDt(regDt);
				
				memList.add(mv);
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
		
	}

}
