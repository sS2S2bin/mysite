package com.poscodx.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private String regDate;
	private Long userNo;
	private Long hit;
	private Long groupNo;
	private Long orderNo;
	private Long depth;
	
	private String writer;
	private Long writerNo;
	
	public Long getWriterNo() {
		return writerNo;
	}
	public void setWriterNo(Long writerNo) {
		this.writerNo = writerNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public Long getHit() {
		return hit;
	}
	public void setHit(Long hit) {
		this.hit = hit;
	}
	public Long getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}
	@Override
	public String toString() {
		return "BoardVo [toString()=" + super.toString() + "]";
	}
	
	

}
