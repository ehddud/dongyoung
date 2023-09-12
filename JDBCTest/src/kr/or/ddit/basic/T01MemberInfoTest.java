package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.or.ddit.util.JDBCUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class T01MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	// logger 객체 생성
	private static final Logger SQL_LOGGER 
	= LogManager.getLogger("log4jexam.sql.Query");
	
	private static final Logger PARAM_LOGGER 
	= LogManager.getLogger("log4jexam.sql.Parameter"); // logger의 이름
	
	private static final Logger RESULT_LOGGER 
	= LogManager.getLogger(T01MemberInfoTest.class); 
	// => logger 이름이 kr.or.ddit.basic.T01MemberInfoTest로 지정됨
	
	// 싱글톤 패턴
	private static T01MemberInfoTest instance = null;
	private T01MemberInfoTest() {}; // 생성자 메서드 숨김 (싱글톤)
	public static T01MemberInfoTest getInstance() {
		if(instance == null) instance = new T01MemberInfoTest();
		return instance;
	}
	

	public static void main(String[] args) {
		T01MemberInfoTest memObj = T01MemberInfoTest.getInstance();
		memObj.start();
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					selectAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	// 회원정보를 등록하기 위한 메서드
	private void insertMember() {
		
		boolean isExist = false;
		String memId = "";

		///////////////////////////////////////////////////
		// 아이디가 DB상에 존재할 경우 loop 반복
		do {
			
			System.out.println();
			System.out.println("추가할 회원 정보를 입력해 주세요.");
			System.out.print("회원 ID : ");
			
			memId = scan.next(); // 띄어쓰기 전까지만 가져옴
			
			isExist = checkMember(memId);
			
			if(isExist) {
				System.out.println("회원 ID가 " + memId + "인 회원이 이미 존재합니다.");
				System.out.println("다시 입력해 주세요.");
				
			}
			
		}while(isExist);
		
		///////////////////////////////////////////////////
		// 아이디가 DB상에 존재하지 않을 경우 회원 가입 진행
		
		System.out.print("회원 이름 : ");
		String memName = scan.next();

		System.out.print("회원 전화번호 : ");
		String memTel = scan.next();
		
		scan.nextLine(); // 입력 버퍼 비우기 (enter키 버퍼에서 제거)
		
		System.out.print("회원 주소 : ");
		String memAddr = scan.nextLine();
		
		///////////////////////////////////////////////////
		
		try {

			// getConnection(url, user, pw);
			conn = JDBCUtil3.getConnection();
			String sql = "INSERT INTO MYMEMBER ";
			sql += " (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) ";
			sql += " VALUES (? ,? , ?, ?)";
			
			SQL_LOGGER.debug("SQL : " + sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			PARAM_LOGGER.warn("Parameter : " + "memId=" + memId + ", memName="+memName
					+ ", memTel=" + memTel + ", memAddr="+memAddr);
			
			
			int cnt = pstmt.executeUpdate();
			
			RESULT_LOGGER.debug("cnt=" + cnt);
			
			if(cnt > 0) {
				System.out.println("회원 등록 성공");
			}else {
				System.out.println("회원 등록 실패");
			}
			
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}
	
	// 회원 정보를 수정하기 위한 메서드
	private void updateMember() {
		
		boolean isExist = false;
		String memId = "";

		///////////////////////////////////////////////////
		// 아이디가 DB상에 존재할 경우 loop 반복
		do {
			
			System.out.println();
			System.out.println("수정할 회원 정보를 입력해 주세요.");
			System.out.print("회원 ID : ");
			
			memId = scan.next(); // 띄어쓰기 전까지만 가져옴
			
			isExist = checkMember(memId);
			
			if(!isExist) {
				System.out.println("회원 ID가 " + memId + "인 회원이 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
				
			}
			
		}while(!isExist);
		
		///////////////////////////////////////////////////
		// 아이디가 DB상에 존재하지 않을 경우 회원 가입 진행
		
		System.out.print("회원 이름 : ");
		String memName = scan.next();

		System.out.print("회원 전화번호 : ");
		String memTel = scan.next();
		
		scan.nextLine(); // 입력 버퍼 비우기 (enter키 버퍼에서 제거)
		
		System.out.print("회원 주소 : ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "UPDATE MYMEMBER " 
					+ " SET MEM_NAME = ?, "  
					+ " MEM_TEL = ?, " 
					+ " MEM_ADDR = ? " 
					+ " WHERE MEM_ID= ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "님의 회원정보가 정상적으로 수정되었습니다.");
			}else {
				System.out.println(memId + "님의 회원정보 수정에 실패했습니다.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}
	
	// 회원 정보를 삭제하기 위한 메서드
	private void deleteMember() {
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력해 주세요.");
		System.out.print("회원 ID : ");
		
		String memId = scan.next();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "님의 회원정보가 삭제되었습니다.");
			}else {
				System.out.println(memId + "님의 회원정보 삭제가 실패하였습니다!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}
	
	// 전체 회원 자료 조회를 출력하기 위한 메서드
	private void selectAll() {
		
		try {
			conn = JDBCUtil3.getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM MYMEMBER";
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("----------------------------------------------------------------------");
			System.out.println("ID\t생성일\t이름\t전화번호\t\t주소");
			System.out.println("----------------------------------------------------------------------");
			
			while(rs.next()) {
				String memId = rs.getString("MEM_ID");
				Date regDate = rs.getTimestamp("REG_DT");
				String memName = rs.getString("MEM_NAME");
				String memTel = rs.getString("MEM_TEL");
				String memAddr = rs.getString("MEM_ADDR");

				System.out.println(memId+"\t"+regDate+"\t"
								  +memName+"\t"+memTel+"\t"+memAddr);
				
			}
			System.out.println("----------------------------------------------------------------------");
			System.out.println("출력 작업 끝");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		
	}
	
	
	// 회원 ID가 존재하는지 체크하기 위한 메서드
	private boolean checkMember(String memId) {
		
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
			// TODO: handle exception
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return isExist;

	}
	

}






