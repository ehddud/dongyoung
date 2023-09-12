package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.comm.VO.AtchFileVO;
import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		IMemberService memService = MemberServiceImpl.getInstance();

		MemberVO mv = memService.getMember(memId);
		
		req.setAttribute("mv", mv);
		
		if(mv.getAtchFileId() > 0) {
			
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			
			AtchFileVO atchFileVO = new AtchFileVO();
			
			atchFileVO.setAtchFileId(mv.getAtchFileId());
					
			List<AtchFileVO> fileList = fileService.getAtchFileList(atchFileVO);
			
			req.setAttribute("fileList", fileList);
			
		}
		
		req.getRequestDispatcher("/views/member/updateForm.jsp")
			.forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr"); 
		String atchFileId = req.getParameter("atchFileId"); 
		
		System.out.println(memId);
		System.out.println(memName);
		System.out.println(memTel);
		System.out.println(memAddr);
		System.out.println(atchFileId);
		
		
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = null;
		
		try {
			
			atchFileVO = fileService.saveAtchFileList(req.getParts());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		
		// 새로운 첨부파일이 존재하지 않는 경우/파일이 아무것도 없었던 경우
		if(atchFileVO == null) { 
			
			mv.setAtchFileId(Long.parseLong(atchFileId));
			
		}else {
			mv.setAtchFileId(atchFileVO.getAtchFileId());
		}
		
		int cnt = memService.modifyMember(mv);
		
		String msg = "";
		
		if(cnt > 0 ) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		HttpSession session = req.getSession();
		
		session.setAttribute("msg", msg);
		
		req.setAttribute("msg", msg);
		
//		req.getRequestDispatcher("/member/list.do").forward(req, resp);
		resp.sendRedirect(req.getContextPath() +  "/member/list.do");
	}
	
	
	
}
