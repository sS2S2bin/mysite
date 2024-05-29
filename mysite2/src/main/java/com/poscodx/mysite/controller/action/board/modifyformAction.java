package com.poscodx.mysite.controller.action.board;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class modifyformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNo = request.getParameter("bno");
		String authNo = request.getParameter("bywho");
		BoardVo board = new BoardVo();
		board = new BoardDao().findbyNo(Long.parseLong(boardNo), Long.parseLong(authNo));
		
		if(board !=null) {
			request.setAttribute("board", board);
			request.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(request, response);
			return ;
		}
		response.sendRedirect(request.getContextPath()+"/board?a=view&bno="+boardNo+"&modifyaccess=fail");
		return;
	}

}
