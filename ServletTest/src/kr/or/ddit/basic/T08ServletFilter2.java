package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T08ServletFilter2 implements Filter{

	/*
	 * 서블릿 필터란
	 * 
	 * 1. 사용목적
	 * - 클라이언트의 요청을 수행하기 전에 가로채 필요한 작업을 수행할 수 있다
	 * - 클라이언트에게 응답정보를 제공하기 전에 응답정보에 필요한 작업을
	 * 	  수행할 수 있다
	 * 
	 * 2. 사용 예
	 * - 인증 필터
	 * - 데이터 압축 필터
	 * - 인코딩 필터
	 * - 로깅 및 감사처리 필터
	 * - 이미지 변호나 필터 등
	 */

	@Override
	public void destroy() {
		System.out.println("T08ServletFilte2r => destroy() 호출됨.");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 실제 filter 관련 메서드 
		System.out.println("T08ServletFilter2 => doFilter() 시작");
		
		// 서블릿 수행시간 계산하기
		long startTime = System.nanoTime();
		
		// 필터 체인을 실행한다. (요청 및 응답객체 전달)
		chain.doFilter(req, resp);
		
		System.out.println("수행시간(ns) : " + (System.nanoTime() - startTime));
		
		
		
		System.out.println("T08ServletFilter2 => doFilter() 끝");
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 처음에 한번만 실행되어야 할 메서드
		System.out.println("T08ServletFilter2 => init() 호출됨.");
		
		// 초기화 파라미터정보 가져오기
		String initParam = filterConfig.getInitParameter("init-param");
		
		System.out.println("init-param2 : " + initParam);
		
	}
	
}
