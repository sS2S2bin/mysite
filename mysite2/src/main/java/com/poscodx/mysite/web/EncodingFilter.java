package com.poscodx.mysite.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

public class EncodingFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		/* request 
		 * 위에 체크했던 변수 중에 exist가 false면 바꾸기
		 * */
		req.setCharacterEncoding("utf-8");
		chain.doFilter(req, res); //chain안의 다음 코드가 실행됨 맨마지막은 servlet이니까 doGet으로 끝남
		
		/* response 처리
		 * */
		
	}
	
}
