package homework.board.service;

import java.sql.SQLException;
import java.util.List;

import homework.T01Homework1JDBCUtil;
import homework.board.dao.T01Homework1BoardDAO;
import homework.board.vo.T01Homework1BoardVO;

public class T01Homework1BoardService {

	private static T01Homework1BoardService boardService;
	
	private T01Homework1BoardDAO boardDAO;
	
	private T01Homework1BoardService() {
		boardDAO = T01Homework1BoardDAO.getInstance();
	}
	public static T01Homework1BoardService getInstance() {
		if(boardService == null) boardService = new T01Homework1BoardService();
		return boardService;
	}
	
	public int writePost(T01Homework1BoardVO bv) {
		
		int cnt = boardDAO.insertPost(bv);
		
		
		return cnt;
		
	}
	
	public List<T01Homework1BoardVO> allPost(){
		
		List<T01Homework1BoardVO> postList = boardDAO.selectAll();
		
		return postList;
		
	}
	
	public int modifyPost(int boardNo, T01Homework1BoardVO bv) {
		
		int cnt = boardDAO.updatePost(boardNo, bv);
		
		return cnt;
		
	}
	
	public int removePost(int boardNo) {
		
		int cnt = boardDAO.deletePost(boardNo);
		
		return cnt;
		
	}
	
	public T01Homework1BoardVO getOnePost(int boardNo) {
		
		T01Homework1BoardVO bv = boardDAO.selectOne(boardNo);
		
		return bv;
		
	}
	
	
	
	public boolean checkNo(int boardNo) {
		
		return boardDAO.isSelected(boardNo);
		
		
	}
	
}

