package kr.or.ddit.comm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyCharacterEncodingFilter implements Filter{

	private String encoding;
	
	@Override
	public void destroy() {

		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		req.setCharacterEncoding(this.encoding);
		resp.setCharacterEncoding(this.encoding);
		
		chain.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {

		if(fc.getInitParameter("encoding") == null) {
			
			this.encoding = "UTF-8";
			
		}else { 
			
			this.encoding = fc.getInitParameter("encoding");
			
		}
		
		System.out.println("현재 설정된 인코딩 정보 : " + this.encoding);
		
	}

	
	
}
