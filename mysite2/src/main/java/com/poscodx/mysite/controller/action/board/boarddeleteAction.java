package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;

public class boarddeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNo = request.getParameter("bno");
		String authNo = request.getParameter("bywho");

		String result = "fail";
		result = 1 == ( new BoardDao().deleteByNo(Long.parseLong(boardNo), Long.parseLong(authNo)) ) ? "success" : "fail";
		
		
		response.sendRedirect(request.getContextPath()+"/board?deleteresult="+result);

	}

}
