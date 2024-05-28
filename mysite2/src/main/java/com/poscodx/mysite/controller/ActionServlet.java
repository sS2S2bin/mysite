package com.poscodx.mysite.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected abstract Action getAction(String actionName);

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String actionName = Optional.ofNullable(req.getParameter("a")).orElse("");
		// Optional.ofnullable로 널인지 확인하고 null 이라면 orElse에 적힌 값으로 셋팅한다.
		
		
		// 값으로 다룰때는 위처럼 사용할 수 있지만, action이 되어야하는 지금은 안돼 
		Action action = getAction(actionName);
		if (action == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return; //request날려도자바 코드는 계솔 실행되기 때문에
		}
		
		action.execute(req,resp);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	public static interface Action{
		void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
		
	}
	
	
	

}
