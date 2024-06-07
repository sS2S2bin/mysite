package com.poscodx.mysite.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	private SqlSession sqlSession;
	
	public GuestbookRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	


//	public void deleteByNo(Long no, String pw) {
//		try(
//			Connection conn = getConnection(); 
//			PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where no=? and password=?");  
//		){
//			pstmt.setLong(1, no);
//			pstmt.setString(2, pw);
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			System.out.println("error : "+e);
//		}
//		
//	}
	
	public int deleteByNoAndPassword(Long no, String pw) {
		return sqlSession.delete("guestbook.deleteByNoAndPassword", Map.of("no",no, "password", pw));
	}


	public int insert(GuestbookVo vo) {
		System.out.println(vo);
		return sqlSession.insert("guestbook.insert", vo);

	}
	
	public List<GuestbookVo> findAll(){
		return sqlSession.selectList("guestbook.findAll");
	}
}
