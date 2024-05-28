package com.poscodx.mysite.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.controller.action.guestbook.GuestbookMainAction;
import com.poscodx.mysite.controller.action.guestbook.deleteAction;
import com.poscodx.mysite.controller.action.guestbook.deleteformAction;
import com.poscodx.mysite.controller.action.guestbook.insertAction;
import com.poscodx.mysite.controller.action.main.MainAction;
import com.poscodx.mysite.controller.action.user.JoinAction;
import com.poscodx.mysite.controller.action.user.JoinFormAction;
import com.poscodx.mysite.controller.action.user.JoinSuccess;
import com.poscodx.mysite.controller.action.user.LoginAction;
import com.poscodx.mysite.controller.action.user.LoginFormAction;
import com.poscodx.mysite.controller.action.user.LogoutAction;
import com.poscodx.mysite.controller.action.user.UpdateAction;
import com.poscodx.mysite.controller.action.user.UpdateFormAction;
import com.poscodx.mysite.dao.GuestbookDao;
import com.poscodx.mysite.vo.GuestbookVo;


public class GuestbookServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Action> mapAction = Map.of(
			"insert", new insertAction(),
			"deleteform", new deleteformAction(),
			"delete", new deleteAction()
			);
	@Override
	protected Action getAction(String actionName) {
		return  mapAction.getOrDefault(actionName, new GuestbookMainAction()); //mapAction.get(actionName);

	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		String action = request.getParameter("a");
//		
//		if("insert".equals(action)) {
//			String name = request.getParameter("name");
//			String content = request.getParameter("content");
//			String password = request.getParameter("pass");
//			
//			GuestbookVo vo = new GuestbookVo();
//			vo.setName(name);
//			vo.setContents(content);
//			vo.setPassword(password);
//			
//			new GuestbookDao().insert(vo);
//			
//			response.sendRedirect(request.getContextPath()+"/guestbook");
//			
//		}else if ("deleteform".equals(action)) {
//			request
//				.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp")
//				.forward(request, response);
//		}else if ("delete".equals(action)) {
//			String password = request.getParameter("password");
//			String no = request.getParameter("no");
//			
//			new GuestbookDao().deleteByNo(Long.parseLong(no), password);
//			response.sendRedirect(request.getContextPath()+"/guestbook");
//		}else {
//			List<GuestbookVo> list = new GuestbookDao().findAll();
//			request.setAttribute("list", list);
//			
//			request
//			.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp")
//			.forward(request, response);
//		}
//	}
//
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		doGet(request, response);
//	}


}
