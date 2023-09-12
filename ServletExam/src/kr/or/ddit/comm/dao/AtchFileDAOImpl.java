package kr.or.ddit.comm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comm.VO.AtchFileVO;
import kr.or.ddit.util.MyBatisUtil;

public class AtchFileDAOImpl implements IAtchFileDAO{

	private static IAtchFileDAO atchFileDAO;
	
	private AtchFileDAOImpl() {
		
	}
	// 
	public static IAtchFileDAO getInstance() {
		if(atchFileDAO == null) {
			atchFileDAO = new AtchFileDAOImpl();
		}
		
		return atchFileDAO;
	}
	
	@Override
	public int insertAtchFile(AtchFileVO atchFileVO) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0; 
		
		try {
			
			cnt = session.insert("atchFile.insertAtchFile", atchFileVO);
			session.commit();
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int insertAtchFileDetail(AtchFileVO atchFileVO) {


		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0; 
		
		try {
			
			cnt = session.insert("atchFile.insertAtchFileDetail", atchFileVO);
			session.commit();
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {

		SqlSession session = MyBatisUtil.getInstance(true);
		
		List<AtchFileVO> atchFileList = new ArrayList<AtchFileVO>();
		
		try {
			
			atchFileList = session.selectList
					("atchFile.getAtchFileList", atchFileVO);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {

		SqlSession session = MyBatisUtil.getInstance(true);
		
		AtchFileVO fileVO = new AtchFileVO();
		
		try {
			
			fileVO = session.selectOne
					("atchFile.getAtchFileDetail", atchFileVO);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return fileVO;
	}

}
