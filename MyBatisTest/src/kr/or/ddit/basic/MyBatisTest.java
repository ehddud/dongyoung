package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest {

	public static void main(String[] args) {
		
		// MyBatis를 이용하여 DB자료를 처리하는 작업 순서
		
		// 1. MyBatis의 환경설정 파일을 읽어와 실행시킨다.
		SqlSessionFactory sessionFactory = null;
		
		try {
			
			// 1-1. xml문 읽어오기
			// 설정파일의 인코딩 설정(한글처리를 위해 UTF-8)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			// 1-2. 위에서 읽어온 Reader 객체를 이용하여
			//		실제 작업을 진행할 객체 생성
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close(); // Reader 객체 닫기.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
		
		// 2-1 insert 작업 연습
		System.out.println("insert 작업 시작..");
		
		// 1) 저장할 데이터를 VO에 담는다.
		MemberVO mv = new MemberVO();
		mv.setMemId("d002");
		mv.setMemName("강감찬");
		mv.setMemTel("010-333-2222");
		mv.setMemAddr("경남 진해시");
		
		// 2) SqlSession 객체변수를 이용하여 해당 쿼리문을 실행한다.
		//    형식) SqlSession객체.insert("namespace값.id값", 파라미터객체)
		//    반환값 : 성공한 레코드 수
		SqlSession sqlSession 
			= sessionFactory.openSession(false); // boolean => autoCommit 여부
												// default => false
		
		try {
			// SqlSession에 CRUD의 기능이 들어있음
			int cnt = sqlSession.insert("member.insertMember", mv);
			// int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("insert 작업 성공");
				sqlSession.commit(); // 커밋
			}else {
				System.out.println("insert 작업 실패!!");
				sqlSession.rollback(); // 롤백
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();  // 세션 닫기
		}
		
		System.out.println("-------------------------------------");
		
		
		// 2-2 update 작업 연습
		System.out.println("update 작업 시작..");
		
		MemberVO mv2 = new MemberVO();
		mv2.setMemId("d002");
		mv2.setMemName("이상민");
		mv2.setMemTel("010-333-3333");
		mv2.setMemAddr("대구시");
		
		sqlSession = sessionFactory.openSession(); // autocommit 여부 , default : false
		
		try {
			// update() 메서드의 반환값도 성공한 레코드의 수이다.
			int cnt = sqlSession.update("member.updateMember", mv2);
			
			if(cnt > 0) {
				System.out.println("update 작업 성공");
				sqlSession.commit(); // 커밋
			}else {
				System.out.println("update 작업 실패!!");
			}
			
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			sqlSession.rollback(); // 롤백
		}finally {
			sqlSession.close();
		}

		/////////////////////////////////////////////////////////////////
		System.out.println("-------------------------------------");
		
		// 2-3 delete 작업 연습
		System.out.println("delete 작업 시작..");
		
		sqlSession = sessionFactory.openSession(); // 기본값 : false
		
		try {
			
			int cnt = sqlSession.delete("membe.deleteMember", "d002");
			
			if(cnt > 0) {
				System.out.println("delete 작업 성공");
				sqlSession.commit();
			}else {
				System.out.println("delete 작업 실패!!");
			}
			
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
		

		/////////////////////////////////////////////////////////////////
		System.out.println("-------------------------------------");
		
		// 2-4 select 작업 연습
		// 1) 응답의 결과가 여러개인 경우 (selectAll() 같은 경우?)
		System.out.println("select 연습(결과가 여러개일 경우) 시작...");
		sqlSession = sessionFactory.openSession(true); // select는 트랜잭션 관리가 의미가 없음(autocommit)
		try {
			
			List<MemberVO> memList = sqlSession.selectList("member.selectAll");
				
			for(MemberVO mv3 : memList) {
				System.out.println("회원 ID : " + mv3.getMemId() );
				System.out.println("회원 이름 : " + mv3.getMemName() );
				System.out.println("회원 전화번호 : " + mv3.getMemTel() );
				System.out.println("회원 주소 : " + mv3.getMemAddr() );
				System.out.println("--------------------------------");
			}
			
			if(memList != null) {
				System.out.println("목록 출력 완료!!");
			}else {
				System.out.println("목록이 없습니다.");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		// 2) 응답의 결과가 1개인 경우 (select() 같은 경우?)
		System.out.println("select 연습 (결과가 하나인 경우) 시작...");
		sqlSession = sessionFactory.openSession(true);
		
		try {
			
			// 응답의 결과가 1개가 확실할 경우에는 selectOne() 메서드 사용
			MemberVO mv4 = sqlSession.selectOne("member.getMember", "가그리");

			System.out.println("회원 ID : " + mv4.getMemId() );
			System.out.println("회원 이름 : " + mv4.getMemName() );
			System.out.println("회원 전화번호 : " + mv4.getMemTel() );
			System.out.println("회원 주소 : " + mv4.getMemAddr() );
			System.out.println("--------------------------------");

			
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		
	}
	
}
