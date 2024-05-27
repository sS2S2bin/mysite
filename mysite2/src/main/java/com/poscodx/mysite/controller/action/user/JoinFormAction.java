package com.poscodx.mysite.controller.action.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.controller.IOExceptio;

public class JoinFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOExceptio {
		request
		.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp")
		.forward(request, response);
		
	}
	
}
