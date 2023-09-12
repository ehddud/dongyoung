package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿을 이용한 파라미터 데이터 처리 에제
 * @author PC-06
 */
public class T03ServletParameterTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String[] hobby = req.getParameterValues("hobby");
		
		String rlgn = req.getParameter("rlgn");
		String[] foods = req.getParameterValues("foods");
		
		/////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("<p>username : " + username + "</p>");
		out.println("<p>password : " + password + "</p>");
		out.println("<p>gender : " + gender + "</p>");
		if(hobby != null) {
			for(String hobb : hobby) {
				out.println("<p>hobby : " + hobb + "</p>");
			}
		}
		out.println("<p>rlgn : " + rlgn + "</p>");
		if(foods != null) {
			for(String food : foods) {
				out.println("<p>foods : " + food + "</p>");
			}
		}
		
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			out.println("<p>파라미터 이름 : " + param + "</p>");
		}
		
		String dir = System.getProperty("user.home");
		out.println(dir);
		
		out.println("</body></html>");
		
	}
	

}
