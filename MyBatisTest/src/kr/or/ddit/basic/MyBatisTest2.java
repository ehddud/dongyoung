package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest2 {

	public static void main(String[] args) {

		// MyBatis를 이용하여 DB자료를 처리하는 작업 순서
		// 1. MyBatis의 환경설정 파일을 읽어와 실행시킨다
		SqlSessionFactory sqlSessionFactory = null;
		
		try {
			// 1-1. xml문 읽어오기
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업 수행
		
		// 2-1 insert 작업 연습
		System.out.println("insert 작업 시작");
		
		// 1) 저장할 데이터를 VO에 담는다
		MemberVO mv = new MemberVO();
		mv.setMemId("거거지2");
		mv.setMemName("거거거기2");
		mv.setMemTel("020-245-6633");
		mv.setMemAddr("저기");
		
		// 2) SqlSession 객체변수를 이용하여 해당 쿼리문을 실행
		//	   형식) SqlSession객체.insert("namespace값.id값", 파라미터객체)
		//	   반환값 : 성공한 레코드 수
		SqlSession sqlSession = sqlSessionFactory.openSession(false);
		
		try {
			// SqlSession객체에 CRUD기능 들어있음
			int cnt = sqlSession.insert("memberTest.insertMember", mv);
			
			if(cnt > 0) {
				System.out.println("insert 작업 성공");
				sqlSession.commit();
			}else {
				System.out.println("insert 작업 실패");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}

		////////////////////////////////////////////////////////////
		System.out.println("update 작업 시작");
		
		sqlSession = sqlSessionFactory.openSession();
		
		MemberVO mv2 = new MemberVO();
		mv2.setMemId("가그리999");
		mv2.setMemName("가그리");
		mv2.setMemTel("가그리");
		mv2.setMemAddr("가그리");
		
		try {
			
			int cnt = sqlSession.update("memberTest.updateMember", mv2);
			
			if(cnt > 0 ) {
				System.out.println("작업 성공!");
				sqlSession.commit();
			}else {
				System.out.println("작업실패 !");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			sqlSession.close();
			
		}

		
		
	}
	
}
