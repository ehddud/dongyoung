package homework.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import homework.board.vo.T01Homework1BoardVO;
import homework.util.T01MyBatisUtil;

public class T01Homework1BoardDAO {
	
	private static T01Homework1BoardDAO boardDAO;
	private T01Homework1BoardDAO() {};
	public static T01Homework1BoardDAO getInstance() {
		if(boardDAO == null) boardDAO = new T01Homework1BoardDAO();
		return boardDAO;
	}
	
	public int insertPost(T01Homework1BoardVO bv) {
		
		SqlSession session = T01MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = session.insert("member2.insertBoard", bv);
			
			if(cnt > 0) {
				System.out.println("insert 성공");
				session.commit();
			}else {
				System.out.println("insert 실패");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		return cnt;
			
	}
	
	public List<T01Homework1BoardVO> selectAll() {
		
		List<T01Homework1BoardVO> list = new ArrayList<T01Homework1BoardVO>();
		
		SqlSession session = T01MyBatisUtil.getInstance(true);
		
		try {
			
			list = session.selectList("member2.selectAll");
			
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

		return list;
			
	}
	
	public int updatePost(T01Homework1BoardVO bv) {
		
		int result = 0;
		
		SqlSession session = T01MyBatisUtil.getInstance();

		try {
			
			int cnt = session.update("member2.updateBoard", bv);
			
			if(cnt > 0) {
				System.out.println("업데이트 성공!");
				session.commit();
				
			}else {
				System.out.println("업데이트 실패");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
	
		return result;
	}
	
	public int deletePost(int boardNo) {
		
		int result = 0;
		
		SqlSession session = T01MyBatisUtil.getInstance();
		
		try {
			
			int cnt = session.delete("member2.deleteBoard", boardNo);
			
			if(cnt > 0) {
				System.out.println("삭제 성공");
				session.commit();
			}else {
				System.out.println("삭제실패");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		return result;
	}

	public T01Homework1BoardVO selectOne(int boardNo) {
		
		T01Homework1BoardVO bv = null;
		
		SqlSession session = T01MyBatisUtil.getInstance(true);
		
		try {
			
			bv = session.selectOne("member2.getBoard", boardNo);

			if(bv != null) {
				System.out.println("select 완료!");
			}else {
				System.out.println("select 실패!");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return bv;
	}
	
	
	public boolean isSelected(int boardNo) {
		
		
		boolean isExist = false;
		
		SqlSession session = T01MyBatisUtil.getInstance();
		
		try {
			
			int cnt = session.selectOne("member2.checkPost", boardNo);
			
			if(cnt > 0) {
				isExist = true;
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return isExist;
		
		
	}
	
}
