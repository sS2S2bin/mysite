package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class viewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("bno");
		System.out.println("view action"+no);
		BoardVo boardvo = new BoardDao().findbyNo(Long.parseLong(no));
		
		request.setAttribute("board", boardvo);
		request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);

	}

}
