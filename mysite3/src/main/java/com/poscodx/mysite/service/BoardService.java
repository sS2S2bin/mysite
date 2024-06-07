package com.poscodx.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;

@Service
public class BoardService {
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public void addContents(BoardVo vo) {
//		if(vo.getGroupNo()!=null) {
//			boardRepository.updateOrderNo(.);
//			boardRepository.insert(vo);
//		}
	}
	public BoardVo getContents(Long no) {
		BoardVo vo = boardRepository.findByNo(no);
		if(vo!=null) {
			boardRepository.updateHit(no);
		}
		return vo;
	}
	
	public BoardVo getContents(Long boardNo, Long userNo) { // 수정할 때 쓰라??
		
	}
	public void updateContents(BoardVo vo) {//글수정할떄 
		
	}
	
	public void deleteContents(Long boardNo, Long userNo) {
		
	}
	
	public Map<String, Object> getContentsList(int currentPage) { // , String keyword
		List<BoardVo> list = null;
		Map<String, Object> map = new HashMap<>();
		
		// 현재 페이지에 보여줄 게시글 리스트 
		list = boardRepository.findbyPage(--currentPage);
		
		// 페이지 시작 번호 계산 
		int start = (int) (((currentPage-1)/5)*5+1);
		
		// 페이지 끝 번호 계산 (총 페이지 수)
		int total = (int) ((boardRepository.countAllboard()-1)/5+1);
		
		
		map.put("list", list);
		map.put("p", currentPage);
		map.put("start", start);
		map.put("total", total);
		map.put("boardlist", list);
				
//		map.put("keyword", 0);
//		
//		map.put("totalCount", 0);
//		map.put("listSize", 0);
//		map.put("currentPage", 0);
//		map.put("beginPage", 0);
//		map.put("endPage", 0);
//		map.put("prev", 0);
//		map.put("next", 0);
		
		return map;
	}
	
}
