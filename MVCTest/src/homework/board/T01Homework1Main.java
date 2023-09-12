package homework.board;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import homework.T01Homework1JDBCUtil;
import homework.board.service.T01Homework1BoardService;
import homework.board.vo.T01Homework1BoardVO;

// 기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
public class T01Homework1Main {

	
	private Scanner sc = new Scanner(System.in);
	
	private T01Homework1BoardService boardService = T01Homework1BoardService.getInstance();
	
	public static void main(String[] args) {
		
		T01Homework1Main boardObj = new T01Homework1Main();
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
					allPost();
					break;
				case 2:
					insertPost();
					break;
				case 3:
					updatePost();
					break;
				case 4:
					deletePost();
					break;
				case 5:
					onePost();
					break;
				case 6:
					System.exit(1);
					break;
				default:
					System.out.println("올바른 값을 입력해 주세요1.");
					break;
				}
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해 주세요2.");
				sc.nextLine();
			}
			
		}
		
	}
	
	public void allPost() {
		
		System.out.println("================전체 게시글================");
		
		System.out.println("번호\t제목\t글쓴이\t작성일\t\t\t내용");

		List<T01Homework1BoardVO> boardList = boardService.allPost();
		
		Collections.sort(boardList);
		
		for(T01Homework1BoardVO li : boardList) {
			String no = li.getNo();
			String title = li.getTitle();
			String writer = li.getWriter();
			String regDt = li.getRegDtDisplay();
			String cont = li.getCont();
			
			System.out.println(no+"\t"+title+"\t"+writer+"\t"+regDt+"\t"+cont);
		}
	
		
		
	}
	
	public void insertPost() {
		
		System.out.println("================게시글 작성================");
		
		System.out.print("제목 >> ");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.print("작성자 >> ");
		String writer = sc.nextLine();
		System.out.print("내용 >> ");
		String cont = sc.nextLine();

		
		T01Homework1BoardVO bv = new T01Homework1BoardVO(title, writer, cont);
		
		int cnt = boardService.writePost(bv);
		
		if(cnt > 0 ) {
			System.out.println("글 작성 성공!!!");
		}else {
			System.out.println("글 작성 실패!!!");
		}
		
		
		
	}
	
	public void updatePost() {
		
		
		System.out.println("================게시글 수정================");

		sc.nextLine();
		System.out.print("수정할 게시글 번호 >> ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		if(!boardService.checkNo(boardNo)) {
			System.out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		System.out.print("제목 >> ");
		String title = sc.nextLine();
		System.out.print("글쓴이 >> ");
		String writer = sc.nextLine();
		System.out.print("내용 >> ");
		String cont = sc.nextLine();
		
		T01Homework1BoardVO bv = new T01Homework1BoardVO(title, writer, cont);
		
		boardService.modifyPost(boardNo, bv);
		
	}
	
	public void deletePost() {
		int boardNo = 0;
		
		System.out.println("================게시글 삭제================");

		sc.nextLine();
		System.out.print("삭제할 게시글 번호 >> ");
		boardNo = sc.nextInt();
		sc.nextLine();
		
		if(!boardService.checkNo(boardNo)) {
			System.out.println("해당 번호의 게시물이 없습니다.");
			return;
		}

		boardService.removePost(boardNo);
		
	}
	
	public void onePost() {

		System.out.println("================게시글 검색================");
		
		System.out.print("검색할 게시글 번호를 입력해주세요 >> ");
		int boardNo = sc.nextInt();
		
		if(!boardService.checkNo(boardNo)) {
			System.out.println("없는 게시글 번호입니다.");
			return;
		}
		
		System.out.println("번호\t제목\t글쓴이\t작성일\t\t\t내용");
		
		T01Homework1BoardVO bv = boardService.getOnePost(boardNo);
		
		String no = bv.getNo();
		String title = bv.getTitle();
		String writer = bv.getWriter();
		String regDt = bv.getRegDtDisplay();
		String cont = bv.getCont();
		
		System.out.println(no+"\t"+title+"\t"+writer+"\t"+regDt+"\t"+cont);
			
			
	}
	

}
