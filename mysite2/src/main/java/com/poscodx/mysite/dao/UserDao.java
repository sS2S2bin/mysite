package com.poscodx.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscodx.mysite.vo.UserVo;


public class UserDao {
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
			PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where no=? and password=?");  
		){
			pstmt.setLong(1, no);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error : "+e);
		}
		
	}

	public void insert(UserVo vo) {
		try(
				Connection conn = getConnection(); 
				PreparedStatement pstmt1 = conn.prepareStatement("insert into user values(null,?,?,password(?),?,current_date())");  
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from user");  
					
			){
				pstmt1.setString(1, vo.getName());
				pstmt1.setString(2, vo.getEmail());
				pstmt1.setString(3, vo.getPassword());
				pstmt1.setString(4, vo.getGender());
				pstmt1.executeUpdate();
				
				
				ResultSet rs = pstmt2.executeQuery();
				vo.setNo(rs.next() ? rs.getLong(1):null);
				rs.close();
				
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}

		
	}
	
	public List<UserVo> findAll(){
		List<UserVo> result = new ArrayList<>();
		
		try(
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("select no,name,password,email,gender,reg_date from user order by no desc");  
					
			
			){
			
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					UserVo vo = new UserVo();
					
					Long no = rs.getLong(1);
					String name = rs.getString(2);
					String pw = rs.getString(3);
					String email = rs.getString(4);
					String gender = rs.getString(5);
					String regDate = rs.getString(6);
					
					vo.setNo(no);
					vo.setName(name);
					vo.setPassword(pw);
					vo.setGender(gender);
					vo.setJoinDate(regDate);
					
					result.add(vo);
				}
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}

		
		return result;
	}
}
