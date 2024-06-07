package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	private SqlSession sqlSession;
	
	public BoardRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
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
	

	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
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
	public BoardVo findByNo(Long no){
		return sqlSession.selectOne("board.findByNo",no);
	}
	
	public BoardVo findbyNo(Long no, Long authNo){
		return sqlSession.selectOne("board.findByNo",Map.of("no",no,"authNo", authNo));
	}
	
//	public List<BoardVo> findAll(){
//		List<BoardVo> result = new ArrayList<>();
//		
//		try(
//				Connection conn = getConnection(); 
//				PreparedStatement pstmt = conn.prepareStatement(
//						"select b.no,title,contents,hit,b.reg_date,g_no,o_no,depth,user_no, u.name,u.no as userNo "
//						+ "from board b, user u "
//						+ "where u.no = b.user_no "
//						+ "order by g_no desc, o_no asc");  
//					
//			
//			){
//			
//				ResultSet rs = pstmt.executeQuery();
//				
//				while(rs.next()) {
//					BoardVo vo = new BoardVo();
//					
//					Long no = rs.getLong(1);
//					String title = rs.getString(2);
//					String contents = rs.getString(3);
//					Long hit = rs.getLong(4);
//					String regDate = rs.getString(5);
//					Long groupNo = rs.getLong(6);
//					Long orderNo = rs.getLong(7);
//					Long depth = rs.getLong(8);
//					Long userNo = rs.getLong(9);
//					
//					String writer = rs.getString(10);
//					Long writerNo = rs.getLong(11);
//					
//					vo.setNo(no);
//					vo.setTitle(title);
//					vo.setContent(contents);
//					vo.setHit(hit);
//					vo.setRegDate(regDate);
//					vo.setGroupNo(groupNo);
//					vo.setOrderNo(orderNo);
//					vo.setDepth(depth);
//					vo.setUserNo(userNo);
//					vo.setWriter(writer);
//					vo.setWriterNo(writerNo);
//					
//					
//					result.add(vo);
//				}
//				
//				rs.close();
//			} catch (SQLException e) {
//				System.out.println("error : "+e);
//			}
//
//		
//		return result;
//	}


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
	public int deleteByNo(long boardNo, long authNo) {
		int result = 0;
		try(
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("delete from board where no=? and user_no=?");  
			){
				pstmt.setLong(1, boardNo);
				pstmt.setLong(2, authNo);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}
		return result;
	}


	public int update(BoardVo board) {
		int result = 0;
		
		try(
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(
						"update board set title=? , contents=? where no=?"
						);
			){
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setLong(3, board.getNo());
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}
		
		return result;
		
	}


	public int reply(long bNo, BoardVo vo) {
		int result = 0;
		
		try(
				Connection conn = getConnection(); 
				// g_no, o_no, depth 알아오기
				PreparedStatement pstmt0 = conn.prepareStatement("select g_no, o_no, depth from board where no=?");
				
				// update하기
				PreparedStatement pstmt1 = conn.prepareStatement("update board set o_no=o_no+1 where g_no=? and o_no>=?");
				
				// insert하기
				PreparedStatement pstmt2 = conn.prepareStatement("insert into board"
						+ "  values(null,?,?,?, now(), ?,?,?,?)");  
				
				// 확인
				PreparedStatement pstmt3 = conn.prepareStatement("select last_insert_id() from board");  
			){
				// g_no, o_no, depth
				pstmt0.setLong(1,bNo);
				ResultSet rs0 = pstmt0.executeQuery();
				Long oNo = null;
				Long depth = null;
				Long gNo = null;
				if(rs0.next()) {
					gNo = rs0.getLong(1);
					oNo = rs0.getLong(2)+1;
					depth = rs0.getLong(3)+1;
				}
				rs0.close();
				
				// update
				pstmt1.setLong(1, gNo);
				pstmt1.setLong(2,oNo);
				pstmt1.executeUpdate();
				
				
				// insert
				pstmt2.setString(1, vo.getTitle());
				pstmt2.setString(2, vo.getContent());
				pstmt2.setLong(3, 0); //hit
				
				pstmt2.setLong(4, gNo);
				pstmt2.setLong(5, oNo);
				pstmt2.setLong(6, depth);
				pstmt2.setLong(7, vo.getUserNo());
				pstmt2.executeUpdate();
			
				// vo no 넘겨주기 
				ResultSet rs3 = pstmt3.executeQuery();
				vo.setNo(rs3.next() ? rs3.getLong(1):null);
				rs3.close();
				
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}
		
		return result;
		
		
	}

	public int countAllboard() {
		return sqlSession.selectOne("board.countAllboard");
	}
	public List<BoardVo> findbyPage(long currentPage) {
		return sqlSession.selectList("board.findbyPage", currentPage);
	}


	public void updateHit(Long no) {
		sqlSession.update("board.updateHit", no);
		
	}
}
