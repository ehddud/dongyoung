package homework.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class T01MyBatisUtil {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		try {
			// 설정파일 encoding 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("config2/mybatis-config.xml");
			
			// SqlSessionFactory 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}

	public static SqlSession getInstance() {
		return sqlSessionFactory.openSession();
	}
	
	public static SqlSession getInstance(boolean autoCommit) {
		return sqlSessionFactory.openSession(autoCommit);
	}
	
}
