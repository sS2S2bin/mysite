package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class writeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String userNo = request.getParameter("writer");
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(contents);
		vo.setUserNo(Long.parseLong(userNo));
		new BoardDao().insert(vo);
		
		
		response.sendRedirect(request.getContextPath()+"/board");
		return;

	}

}
