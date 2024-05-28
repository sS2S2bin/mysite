package com.poscodx.mysite.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// no 값은 session 에서 가져와야해 (보안) 조작한다.
		HttpSession session = request.getSession();
		
		
		if(session==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		//action
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo vo = new UserVo();
		vo.setName(name);
		if(password!=null) {
			vo.setPassword(password);	
		}
		vo.setGender(gender);
		
	    int success = new UserDao().updateByNo(authUser.getNo(), vo);
		
		//request.getRequestDispatcher("/WEB-INF/views/user/updateform.jsp")
		//.forward(request, response);
		
		response.sendRedirect(request.getContextPath());
		return;
	}

}
