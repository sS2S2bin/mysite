package com.poscodx.mysite.controller.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.GuestbookDao;
import com.poscodx.mysite.vo.GuestbookVo;

public class insertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String password = request.getParameter("pass");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setContents(content);
		vo.setPassword(password);
		
		new GuestbookDao().insert(vo);
		
		response.sendRedirect(request.getContextPath()+"/guestbook");
	}

}
