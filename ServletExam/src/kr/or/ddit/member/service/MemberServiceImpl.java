package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImplWithMybatis;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private IMemberDAO memDAO;
	
	private static IMemberService memService;
	
	private MemberServiceImpl() {
		// Interface 기반으로 코딩해서 가능 
		// (IMemberDAO을 구현한 class들이라 가능 => 다형성)
		memDAO = MemberDAOImplWithMybatis.getInstance();
//		memDAO = MemberDAOImplWithJDBC.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		
		return memService;
	}
	
	
	@Override
	public int registMember(MemberVO mv) {

		int cnt = memDAO.insertMember(mv);
		
		// 내 계좌에서 100만원 인출
		// DAO에 업데이트
		
		// 상대 계좌에 100만원 입금
		// DAO에 업데이트
		
		return cnt;
	}

	@Override
	public int modifyMember(MemberVO mv) {
		
		int cnt = memDAO.updateMember(mv);
		
		return cnt;
	}


	@Override
	public int removeMember(String memId) {
		
		int cnt = memDAO.deleteMember(memId);
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean isExist = memDAO.checkMember(memId);
		
		return isExist;
		
	}

	@Override
	public MemberVO getMember(String memId) {
		
		MemberVO mv = memDAO.getMember(memId);
		
		return mv;
		
	}

	@Override
	public List<MemberVO> selectAll() {

		List<MemberVO> memList = memDAO.selectAll();
		
		return memList;
		
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {

		List<MemberVO> memList = memDAO.searchMember(mv);
		
		return memList;
		
	}

}
