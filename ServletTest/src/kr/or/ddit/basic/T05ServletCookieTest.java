package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T05ServletCookieTest extends HttpServlet{

	/*
	 * 서블릿 쿠키에 대하여
	 * (웹서버와 브라우저는 애플리케이션을 사용하는 동안 필요한 값을 
	 * 쿠키를 통해 공유하여 상태를 유지함.)
	 * 1. 구성요소
	 * -이름 : 각각의 쿠키를 구별하는데 사용되는 이름
	 * -값 : 쿠키의 이름과 관련된 값
	 * -유효시간(초) : 쿠키의 유지시간
	 * -도메인 : ex) www.somehost.com, .somehost.com 등 쿠키를 전송할 도메인
	 * -경로 : 쿠키를 공유할 기준 경로를 지정한다 
	 * 		=> 지정하지 않으면 실행한 URL의 경로부분 사용
	 * 
	 * 2. 동작방식
	 * - 쿠키생성단계 : 생성한 쿠키를 응답데이터의 헤더에 저장하여 웹브라우저에 전송
	 * - 쿠키저장단계 : 웹브라우저는 응답데이터에 포함한 쿠키를 쿠키 저장소에 보관한다
	 * - 쿠키전송단계 : 웹브라우저는 저장한 쿠키를 요청이 있을 때마다 웹서버에 전송
	 * 				(삭제되기 전까지 계속 전송함)
	 * 				웹 서버는 브라우저가 전송한 쿠키를 사용해서 필요한 작업을 수행한다
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 쿠키 생성(설정) 예제
		setCookieExam(req, resp);
		
		// 쿠키 정보 읽기 예제
		readCookieExam(req, resp);
		
		// 쿠키 정보 삭제 예제
		//deleteCookieExam(req, resp);
		
	}
	



	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// 1. 쿠키를 생성한다. 사용 불가 문자(공백, []()=, "/?@:;) = RFC2965
		Cookie userId 
		= new Cookie("userId", req.getParameter("userId"));
		// 쿠키값에 한글을 사용시 인코딩 처리를 해줘야 한다.
		Cookie name 
		= new Cookie("name", URLEncoder.encode(req.getParameter("name"), "UTF-8"));
		// 2. 쿠키 소멸시간 설정(초단위)
		// => 지정하지 않으면 웹브라우저를 종료할 때 쿠키를 함께 삭제한다.
		userId.setMaxAge(60*60*24); // 초 단위
		userId.setHttpOnly(true); // 자바스크립트를 이용한 직접접근 방지
		
		name.setMaxAge(60*60*48); // 48시간
		
		/////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 응답 헤더에 쿠키를 추가
		resp.addCookie(userId);
		resp.addCookie(name);
		
		PrintWriter out = resp.getWriter();
		
		String title =  "쿠키 설정 예제";
		
		out.println("<!DOCTYPE html><html><head><title>"
				+ title + "</title></head><body>"
				+ "<h1 align=\"center\">" + title + "</h1>"
				+ "<ul><li><b>ID : </b>"
				+ req.getParameter("userId") + "</li>"
				+ "<li><b>이름</b> : "
				+ req.getParameter("name") + "</li></ul>"
				+ "</body></html>");
		for(Cookie c : req.getCookies()) {
			System.out.println("cookieName : " + c.getName());
			System.out.println("cookieValue : " + c.getValue());
		}
		
	}


	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// 현재 도메인에서 사용중인 쿠키객체 배열 가져오기
		Cookie[] cookies = req.getCookies();
		
		///////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키정보 읽기 예제";
		
		
		out.println("<!DOCTYPE html><html><head><title>"
				+ title + "</title></head><body>");
		
		if(cookies != null) {
			out.println("<h2>" + title + "</h2>");
			
			for(Cookie cookie : cookies) {
				out.print("<p>name : " + cookie.getName() + "</p><br>");
				out.print("<p>value : " + cookie.getValue() + "</p><br>");
				
				out.print("<hr>");
				
			}
		}else {
			out.println("<h2>사용중인 쿠키 정보가 없습니다.</h2>");
		}
		
		out.println("</body></html>");
		
	}
	

	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		/*
		 * 사용중인 쿠키 정보를 삭제하는 방법
		 * 1. 사용중인 쿠키 정보를 이용하여 쿠키객체를 생성한다.
		 * 2. 쿠키 객체의 지속시간을 0으로 설정한다(setMaxAge(0))
		 * 3. 설정한 쿠키객체를 응답헤더에 추가하여 전송한다.
		 */
		
		// 사용중인 쿠키 전부 배열로 불러옴
		Cookie[] cookies = req.getCookies();

		/////////////////////////////////////////
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 삭제 예제";
		
		out.println("<!DOCTYPE html><html><head><title>"
				+ title + "</title></head><body>");
		
		if(cookies != null) {
			out.println("<h2>" + title + "</h2>");
			
			for(Cookie cookie : cookies) {
				
				if(cookie.getName().equals("name")) {
				
					// 쿠키 제거하기
					// 쿠키 지속시간 0으로 설정
					cookie.setMaxAge(0);
					// 쿠키 객체를 응답 헤더에 추가하여 전송
					resp.addCookie(cookie);
					
					out.println("<p>삭제한 쿠키 : " + cookie.getName() + "</p><br>" );
					
				}
				
				out.print("<p>name : " + cookie.getName() + "</p><br>");
				out.print("<p>value : " + cookie.getValue() + "</p><br>");
				
				out.print("<hr>");
				
			}
		}else {
			out.println("<h2>사용중인 쿠키 정보가 없습니다.</h2>");
		}
		
		out.println("</body></html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
