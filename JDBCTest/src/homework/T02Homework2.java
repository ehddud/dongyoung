package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
public class T02Homework2 {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner sc = new Scanner(System.in);
	
	private static T02Homework2 instance;
	private T02Homework2() {};
	public static T02Homework2 getInstance() {
		if(instance == null) instance =  new T02Homework2();
		return instance;
	}
	
	public static void main(String[] args) {
		
		T02Homework2 boardObj = T02Homework2.getInstance();
		boardObj.start();
		
	}
	
	
	public void displayMenu() {
		System.out.println("===============게시판===============");
		System.out.println("==============메뉴 선택=============");
		System.out.println("1.전체 게시글 출력 2.새글 작성 3.수정 4.삭제 5.검색 6.종료");
		System.out.print("메뉴 선택 >> ");
		
	}
	
	// 프로그램 시작 메서드
	public void start() {
		int choice;
		
		while(true) {
			displayMenu();
			try {				
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					selectAll();
					break;
				case 2:
					write();
					break;
				case 3:
					update();
					break;
				case 4:
					delete();
					break;
				case 5:
					select();
					break;
				case 6:
					System.exit(1);
					break;
					
				default:
					System.out.println("올바른 값을 입력해 주세요.");
					break;
				}
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해 주세요.");
				sc.nextLine();
			}
			
		}
		
	}
	
	public void selectAll() {
		Map<String, String> row = null;
		T02Homework2VO vo = null;
		List<T02Homework2VO> list = null;
		
		System.out.println("================전체 게시글================");
		
		System.out.println("번호\t제목\t글쓴이\t작성일\t\t\t내용");
		
		String sql = "SELECT * FROM JDBC_BOARD";
		
		try {
			conn = T02Homework2JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<T02Homework2VO>();
			
			while(rs.next()) {

				vo = new T02Homework2VO(rs.getString("BOARD_NO"),rs.getString("BOARD_TITLE"),rs.getString("BOARD_WRITER"),rs.getString("BOARD_CONTENT"),rs.getDate("BOARD_DATE") );
				list.add(vo);
				
				
			}
			Collections.sort(list);
			for(int i = 0 ; i < list.size(); i++) {
				System.out.println(
						list.get(i).getNo() + "\t" +
						list.get(i).getTitle() + "\t" +
						list.get(i).getWriter() + "\t" +
						list.get(i).getDate() + "\t" +
						list.get(i).getCont() + "\t" 
						);
			}
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T02Homework2JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		
	}
	
	public void write() {
		
		String title = "";
		String writer = "";
		String cont = "";
		
		String sql = "INSERT INTO JDBC_BOARD";
		sql += " (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT) ";
		sql += " VALUES (BOARD_SEQ.nextval, ?, ?, ?)";
		
		System.out.println("================게시글 작성================");
		
		sc.nextLine();
		System.out.print("제목 >> ");
		title = sc.nextLine();
		System.out.print("작성자 >> ");
		writer = sc.nextLine();
		System.out.print("내용 >> ");
		cont = sc.nextLine();
		
		try {
			
			conn = T02Homework2JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, cont);
			
			int rs = pstmt.executeUpdate();
			
			if(rs > 0 ) {
				System.out.println("게시글 작성 완료!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			T02Homework2JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		
		
	}
	
	public void update() {
		
		String sql = "UPDATE JDBC_BOARD";
		sql += " SET BOARD_TITLE = ?, BOARD_CONTENT = ? ";
		sql += " WHERE BOARD_NO = ?";
		
		System.out.println("================게시글 수정================");

		sc.nextLine();
		System.out.print("수정할 게시글 번호 >> ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		if(!checkNo(boardNo)) {
			System.out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		System.out.print("제목 >> ");
		String title = sc.nextLine();
		System.out.print("내용 >> ");
		String cont = sc.nextLine();
		
		try {
			
			conn = T02Homework2JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, cont);
			pstmt.setInt(3, boardNo);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("변경 성공");
			}else {
				System.out.println("변경 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T02Homework2JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}
	
	public void delete() {

		String sql = "DELETE FROM JDBC_BOARD";
		sql += " WHERE BOARD_NO = ?";
		
		System.out.println("================게시글 삭제================");

		sc.nextLine();
		System.out.print("삭제할 게시글 번호 >> ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		if(!checkNo(boardNo)) {
			System.out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		try {
			
			conn = T02Homework2JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T02Homework2JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
	}
	
	public void select() {

	      LinkedHashMap<String,Object> row = null;
	      T02Homework2VO vo = null;
		
		System.out.println("================게시글 검색================");
		
		System.out.print("검색할 게시글 번호를 입력해주세요 >> ");
		int no = sc.nextInt();
		
		if(!checkNo(no)) {
			System.out.println("없는 게시글 번호입니다.");
			return;
		}
		
		System.out.println("번호\t제목\t글쓴이\t작성일\t\t\t내용");
		
		String sql = "SELECT * FROM JDBC_BOARD WHERE BOARD_NO = ?";
		
		try {
			conn = T02Homework2JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			
			while(rs.next()) {
				row = new LinkedHashMap<String, Object>();
				for(int i = 1; i <=colCount; i++) {
		               String key = rsmd.getColumnLabel(i);
		               Object value = rs.getObject(i);
		               row.put(key, value);
		               vo = new T02Homework2VO(rs.getString("BOARD_NO"),rs.getString("BOARD_TITLE"),rs.getString("BOARD_WRITER"),rs.getString("BOARD_CONTENT"),rs.getDate("BOARD_DATE"));
				}
			}
			
			System.out.println(
					vo.getNo()+ "\t" +
							vo.getTitle() + "\t" +
							vo.getWriter() + "\t" +
							vo.getRegDtDisplay() + "\t" +
							vo.getCont() + "\t" 
					);
				

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			T02Homework2JDBCUtil.close(conn, stmt, pstmt, rs);
		}
			
			
	}
	
	private boolean checkNo(int boardNo) {
		
		try {
			
			conn = T02Homework2JDBCUtil.getConnection();
			
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
		}
		
		return false;
		
		
	}
	
}
