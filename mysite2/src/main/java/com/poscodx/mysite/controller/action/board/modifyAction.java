package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class modifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNo = request.getParameter("bno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		BoardVo board = new BoardVo();
		board.setNo(Long.parseLong(boardNo));
		board.setTitle(title);
		board.setContent(content);
		
		if(new BoardDao().update(board) == 1) {
			response.sendRedirect(request.getContextPath()+"/board?a=view&bno="+boardNo);
			return;
		}
		
		response.sendRedirect(request.getContextPath()+"/board?a=view&bno="+boardNo+"&modifyresult=fail");
		return;
		

	}

}
