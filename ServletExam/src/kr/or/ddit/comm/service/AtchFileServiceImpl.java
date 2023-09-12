package kr.or.ddit.comm.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;


import kr.or.ddit.comm.VO.AtchFileVO;
import kr.or.ddit.comm.dao.AtchFileDAOImpl;
import kr.or.ddit.comm.dao.IAtchFileDAO;

public class AtchFileServiceImpl implements IAtchFileService{

	private static IAtchFileService fileService;
	
	private IAtchFileDAO fileDAO ;
	
	public AtchFileServiceImpl() {
		
		fileDAO = AtchFileDAOImpl.getInstance();
		
	}

	public static IAtchFileService getInstance() {
		if(fileService == null) {
			
			fileService = new AtchFileServiceImpl();
			
		}
		
		return fileService;
	}
	
	@Override
	public AtchFileVO saveAtchFileList(Collection<Part> parts) throws Exception {

		String uploadPath = "D:/D_Other/upload_files";
		
		File uploadDir = new File(uploadPath);
		
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		AtchFileVO atchFileVO = null;
		
		boolean isFirstFile = true; // 첫번째 파일 여부
		
		for(Part part : parts) {
			
			// 파일명 추출
			String fileName = part.getSubmittedFileName();
			
			// fileName이 null도 아니고 빈 문자열("")도 아님
			// => 파일이다
			if(fileName != null && !fileName.equals("")) {
				
				// 파일 저장 기능
				// 처음 파일을 저장하는 경우
				// ATCH_FILE, ATCH_FILE_DETAIL 테이블에 둘다 INSERT
				// 처음 파일을 저장하는 경우가 아니면(2번째 파일을 저장할 때부터)
				// ATCH_FILE_DETAIL에만 저장
				if(isFirstFile) {
					
					isFirstFile = false;
					
					// ATCH_FILE에 저장
					atchFileVO = new AtchFileVO();
					
					fileDAO.insertAtchFile(atchFileVO);
					
				}
				
				String originFileName = fileName;	// 파일명
				long fileSize = part.getSize(); 	// 파일크기
				String saveFileName = "";			// 저장파일명
				String saveFilePath = "";			// 저장파일 경로
				
				saveFileName = UUID.randomUUID().toString().replace("-", "");
				
				
				// 확장자명 추출
				String fileExtension = 
						originFileName.lastIndexOf(".") < 0 ? "" : 
							originFileName
							.substring(originFileName.lastIndexOf(".") + 1);

				saveFilePath = uploadPath + File.separator 
								+ saveFileName +"."+ fileExtension;
				
				// 업로드 파일(원본파일) 저장하기
				part.write(saveFilePath);

				atchFileVO.setFileStreCours(saveFilePath);
				atchFileVO.setStreFileNm(saveFileName);
				atchFileVO.setFileSize(fileSize);
				atchFileVO.setFileExtsn(fileExtension);
//				atchFileVO.setFileCn("");
				atchFileVO.setOrignlFileNm(originFileName);
				
				// ATCH_FILE_DATAIL 테이블에 저장
				fileDAO.insertAtchFileDetail(atchFileVO);
				
				part.delete(); // 임시 업로드 파일 삭제하기
				
			}
			
			
		}
		
		
		return atchFileVO;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
		
		return fileDAO.getAtchFileList(atchFileVO);
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		
		return fileDAO.getAtchFileDetail(atchFileVO);
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
		
	}
	
	
}
