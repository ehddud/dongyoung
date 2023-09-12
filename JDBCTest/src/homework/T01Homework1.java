package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class T01Homework1 {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 싱글톤패턴
	private static T01Homework1 instance = null;
	private T01Homework1() {};
	public static T01Homework1 getInstance() {
		if(instance == null) instance = new T01Homework1();
		return instance;
	}

	
	public static void main(String[] args) {
		

		new T01Homework1().init();
		
		
	}
	
	
	public void init() {
		
		System.out.println("*********************************");
		System.out.println("호텔 문이 열렸습니다.");
		
		while(true) {
			
			displayMenu();
			
			System.out.print("메뉴 선택 >>");
			
			try {
				int select = T01Homework1ScanUtil.nextInt();
				switch(select) {
				case 1:
					checkIn();
					break;
				case 2:
					checkOut();
					break;
				case 3:
					roomState();
					break;
				case 4:
					System.out.println("*********************************");
					System.out.println("호텔 문을 닫았습니다.");
					System.out.println("*********************************");
					System.exit(1);
				default :
					System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
				}
			}catch(InputMismatchException e) {
				System.out.println("숫자만 입력해 주세요!");
				T01Homework1ScanUtil.nextLine();
			}
			
			
		}
		
	}
	
	public void displayMenu() {

		System.out.println("*********************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*********************************");
		
	}
	
	public void checkIn() {
		
		boolean isExist = true;
		int roomNo = 0;
		
		while(isExist) {
			
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 >> ");
			roomNo = T01Homework1ScanUtil.nextInt();
			
			isExist = checkRoom(roomNo);
			
			if(isExist) {
				System.out.println(roomNo + "번 방은 예약된 방입니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		}
		

		
		System.out.println("체크인하실 분의 성함을 입력해주세요.");
		System.out.print("이름 입력 >>");
		String name = T01Homework1ScanUtil.nextLine();
		
		String sql = "INSERT INTO HOTEL (ROOM_NO, MEM_NAME) ";
		sql += " VALUES (?, ?)";

		int rs = 0;
		
		try {
			
			conn = T01Homework1JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			pstmt.setString(2, name);
			
			rs = pstmt.executeUpdate();
			
			if(rs != 0) {
				System.out.println(roomNo+"번방 "+name+"님 체크인 완료!");
			}else {
				System.out.println("체크인 실패");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void checkOut() {
		
		int roomNo = 0;
		
		while(true) {
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			System.out.print("방번호 입력 >> ");
			roomNo = T01Homework1ScanUtil.nextInt();
			
			 
			if(!checkRoom(roomNo)) {
				System.out.println(roomNo + "번 방은 예약되지 않은 방입니다.");
				System.out.println("다시 입력해 주세요.");
			}else {
				break;				
			}
			
			
		}
		
		
		try {
			conn = T01Homework1JDBCUtil.getConnection();
			String sql = "DELETE FROM HOTEL WHERE ROOM_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			pstmt.executeUpdate();
			
			System.out.println(roomNo+"번 방이 체크아웃 되었습니다.");
			System.out.println("처음으로 돌아갑니다.");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void roomState() {

		System.out.println("*********************************");
		LinkedHashMap<Integer, String> obj = new LinkedHashMap<Integer, String>();
		
		try {
			conn = T01Homework1JDBCUtil.getConnection();
			String sql = "SELECT * FROM HOTEL";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs == null) {
				System.out.println("예약된 객실이 없습니다.");
				return;
			}else {
				System.out.println("================================");		
				System.out.println("현재 투숙중인 방의 목록");
				System.out.println("방번호\t투숙객");
				System.out.println("--------------------------------");
				
				while(rs.next()) {

					obj.put(rs.getInt("ROOM_NO"), rs.getString("MEM_NAME"));
				}
				List<Integer> keySet = new ArrayList<Integer>(obj.keySet());
				Collections.sort(keySet);
				for(Integer key : keySet) {
					System.out.println(key +"\t"+obj.get(key));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return;

	}
	
	private boolean checkRoom(int roomNo) {
		
		try {
			
			conn = T01Homework1JDBCUtil.getConnection();
			
			String sql = "SELECT COUNT(*) FROM HOTEL WHERE ROOM_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			
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

