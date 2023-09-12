package kr.or.ddit.comm.dao;

import java.util.List;

import kr.or.ddit.comm.VO.AtchFileVO;

public interface IAtchFileDAO {

	/**
	 * 첨부파일 저장
	 * @param atchFileVO
	 * @return
	 */
	public int insertAtchFile(AtchFileVO atchFileVO);

	/**
	 * 첨부파일 상세정보 저장
	 * @param atchFileVO
	 * @return
	 */
	public int insertAtchFileDetail(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 목록 조회하기
	 * @return atchFileVO
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 조회하기
	 * @return atchFileVO
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO);
	
	
}
