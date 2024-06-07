package com.poscodx.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping({"","/"})
	public String index(Model model) {
		Map map = boardService.getContentsList(1);
		model.addAllAttributes(map);
		
		return "board/index";
	}
	
	@RequestMapping({"/{p}"})
	public String index(@PathVariable("p") int currentPage, Model model) {
		Map map = boardService.getContentsList(currentPage);
		
		model.addAllAttributes(map);
		
		return "board/index";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("board",vo);
		return "board/view";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(HttpSession session, @PathVariable("no") Long no) {
		// access control
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		////////////////////////			
	}
	
}