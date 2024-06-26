package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;

public class writeformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bNo = request.getParameter("bno");
		String reply = request.getParameter("reply");
		if (bNo!=null && reply!=null) {
			request.setAttribute("bno",bNo);
			request.setAttribute("reply", reply);
		}

	
		request.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(request, response);	

	}

}
