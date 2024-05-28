package com.poscodx.mysite.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session에서 얘를 빼면 되겠지?
		HttpSession session = request.getSession();
		if(session!=null) {
			/*log out*/
			session.removeAttribute("authUser");
			session.invalidate(); //session id 바뀌고 기존에 있던 놈 날라감
		}
		
		
		//redirect to main
		response.sendRedirect(request.getContextPath());

	}

}
