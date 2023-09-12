package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * sqlSession 객체 제공하는 sqlSessionFactory 클래스
 * @author PC-06
 *
 */
public class MyBatisUtil {

	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		try {
			// 1-1. xml문 읽어오기
			// 설정파일의 인코딩 설정(한글처리를 위해 UTF-8)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			// 1-2. 위에서 읽어온 Reader 객체를 이용하여
			//		실제 작업을 진행할 객체 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close(); // Reader 객체 닫기.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * SqlSession 객체를 제공하는 factory 메서드
	 * @return Sqlsession 객체
	 */
	public static SqlSession getInstance() {
		return sqlSessionFactory.openSession();
	}
	
	/**
	 * SqlSession 객체를 제공하는 factory 메서드
	 * @param autoCommit 오토커밋 여부
	 * @return Sqlsession 객체
	 */
	public static SqlSession getInstance(boolean autoCommit) {
		return sqlSessionFactory.openSession(autoCommit);
	}
	
}
