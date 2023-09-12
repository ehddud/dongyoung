package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;
import kr.or.ddit.util.MyBatisUtil;

public class MemberDAOImplWithMybatis implements IMemberDAO{

	
	private static IMemberDAO memDAO;
	
	private MemberDAOImplWithMybatis() {};
	
	public static IMemberDAO getInstance() {
		if(memDAO == null) {
			memDAO = new MemberDAOImplWithMybatis();
		}
		
		return memDAO;
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = session.insert("member.insertMember", mv);
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		
		return cnt;
		
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			
			cnt = session.update("member.updateMember", mv);
			
			
			if(cnt > 0) {
				session.commit();
			}
				
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;
		
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			
			cnt = session.delete("member.deleteMember", memId);
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		return cnt;
		
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;

		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			int cnt = session.selectOne("member.checkMember", memId);
			
			if(cnt > 0) {
				isExist = true;
			}else {
				isExist = false;
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return isExist;

		
	}

	@Override
	public List<MemberVO> selectAll() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			memList = session.selectList("member.selectAll");
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return memList;
		

	}

	@Override
	public List<MemberVO> searchMember(MemberVO paramMv) {
		

		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			memList = session.selectList("member.searchMember", paramMv);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return memList;
		
	}

}
