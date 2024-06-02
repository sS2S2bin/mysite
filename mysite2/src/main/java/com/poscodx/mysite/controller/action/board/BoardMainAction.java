package com.poscodx.mysite.controller.action.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class BoardMainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// 시작 페이지 초기화
		Long currentPage = 1L; 
		if(request.getParameter("p")!=null) {
			currentPage = Long.parseLong(request.getParameter("p"));
		}
		
		// 현재 페이지에 보여줄 게시글 리스트 
		List<BoardVo> list = new ArrayList<BoardVo>();
		list = new BoardDao().findbyPage(currentPage);
		
		
		// 페이지 시작 번호 계산 
		int start = (int) (((currentPage-1)/5)*5+1);
		
		// 페이지 끝 번호 계산 (총 페이지 수)
		int total = (int) ((new BoardDao().countAllboard()-1)/5+1);

		
		request.setAttribute("p", currentPage);
		request.setAttribute("start", start);
		request.setAttribute("total", total);
		request.setAttribute("boardlist", list);
		
		request
			.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
			.forward(request, response);

	}

}
