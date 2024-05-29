package com.poscodx.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscodx.mysite.vo.BoardVo;


public class BoardDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
		Class.forName("org.mariadb.jdbc.Driver");
		
		String url = "jdbc:mariadb://192.168.64.3:3306/webdb?charset=utf-8";
		
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: "+e);
		} 
		return conn;
	}
	


	public void deleteByNo(Long no, String pw) {
		try(
			Connection conn = getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement("delete from board where no=? and password=?");  
		){
			pstmt.setLong(1, no);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error : "+e);
		}
		
	}

	public void insert(BoardVo vo) {
		try(
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("select max(g_no) from board");
				PreparedStatement pstmt1 = conn.prepareStatement("insert into board"
						+ "  values(null,?,?,?, now(), ?,?,?,?)");  
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from board");  
					
			){
				ResultSet rs0 = pstmt.executeQuery();
				Long maxGroupNo = rs0.next() ? rs0.getLong(1):null;
				
				
				pstmt1.setString(1, vo.getTitle());
				pstmt1.setString(2, vo.getContent());
				pstmt1.setLong(3, 0);
				
				pstmt1.setLong(4, maxGroupNo+1);
				pstmt1.setLong(5, 1);
				pstmt1.setLong(6, 0);
				pstmt1.setLong(7, vo.getUserNo());
				pstmt1.executeUpdate();
				
				
				ResultSet rs = pstmt2.executeQuery();
				vo.setNo(rs.next() ? rs.getLong(1):null);
				rs.close();
				
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}

		
	}
	public BoardVo findbyNo(Long searchno){
		BoardVo vo = new BoardVo();
		
		try(
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(
						"select b.no,title,contents,hit,b.reg_date,g_no,o_no,depth,user_no, u.name "
						+ "from board b, user u "
						+ "where u.no = b.user_no "
						+ "and b.no = ? "
						+ "order by g_no desc, o_no asc");  
					
			
			){
				pstmt.setLong(1, searchno);
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String contents = rs.getString(3);
					Long hit = rs.getLong(4);
					String regDate = rs.getString(5);
					Long groupNo = rs.getLong(6);
					Long orderNo = rs.getLong(7);
					Long depth = rs.getLong(8);
					Long userNo = rs.getLong(9);
					String writer = rs.getString(10);
					
					vo.setNo(no);
					vo.setTitle(title);
					vo.setContent(contents);
					vo.setHit(hit);
					vo.setRegDate(regDate);
					vo.setGroupNo(groupNo);
					vo.setOrderNo(orderNo);
					vo.setDepth(depth);
					vo.setUserNo(userNo);
					vo.setWriter(writer);
					
				}
				
				rs.close();
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}
		
		return vo;
	}
	
	public List<BoardVo> findAll(){
		List<BoardVo> result = new ArrayList<>();
		
		try(
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(
						"select b.no,title,contents,hit,b.reg_date,g_no,o_no,depth,user_no, u.name "
						+ "from board b, user u "
						+ "where u.no = b.user_no "
						+ "order by g_no desc, o_no asc");  
					
			
			){
			
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVo vo = new BoardVo();
					
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String contents = rs.getString(3);
					Long hit = rs.getLong(4);
					String regDate = rs.getString(5);
					Long groupNo = rs.getLong(6);
					Long orderNo = rs.getLong(7);
					Long depth = rs.getLong(8);
					Long userNo = rs.getLong(9);
					String writer = rs.getString(10);
					
					vo.setNo(no);
					vo.setTitle(title);
					vo.setContent(contents);
					vo.setHit(hit);
					vo.setRegDate(regDate);
					vo.setGroupNo(groupNo);
					vo.setOrderNo(orderNo);
					vo.setDepth(depth);
					vo.setUserNo(userNo);
					vo.setWriter(writer);
					
					
					result.add(vo);
				}
				
				rs.close();
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}

		
		return result;
	}
}
