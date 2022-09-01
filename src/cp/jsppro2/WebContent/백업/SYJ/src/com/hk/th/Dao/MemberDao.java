package com.hk.th.Dao;

import java.sql.*;
import com.hk.th.Vo.*;

public class MemberDao {

	static String DB_URL = "jdbc:mysql://localhost/shopping";
	static String DB_ID = "root";
	static String DB_PW = "mysql";
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	static {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnectDB() {
		Connection tempconn = null;
		if(con ==null) {
			try {
				tempconn = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return tempconn;
	}
	
	public void DBClose() {
		if(con != null) {
			try {con.close(); con = null;} catch (SQLException e) {e.printStackTrace();}
		}
		if(pstmt != null) {
			try {pstmt.close(); pstmt = null;} catch (SQLException e) {e.printStackTrace();}
		}
		if(rs != null) {
			try {rs.close(); rs = null;} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public MemberVo selectMember(String id) {
		MemberVo memVo = new MemberVo();
		String psql = "select * from members where id = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVo.setId(rs.getString("id"));
				memVo.setPw(rs.getString("pw"));
				memVo.setName(rs.getString("name"));
				memVo.setEmail(rs.getString("email"));
				memVo.setPost_num(rs.getInt("post_num"));
				memVo.setAddress(rs.getString("address"));
				memVo.setAddress2(rs.getString("address2"));
				memVo.setBirth(rs.getInt("birth"));
				memVo.setPhone(rs.getInt("phone"));
				memVo.setAdm(rs.getInt("adm"));
				memVo.setPoint(rs.getInt("point"));
				memVo.setMessage(rs.getString("message"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return memVo;
	}
	
	
	public int changeInpo(String pw ,String email, int post_num,String address,String address2,int phone,String message, String id) {
		String psql = "update members set pw = ? , email = ?, post_num = ?, address = ? , address2 = ? , phone = ?, message=? where id = ? ";
		int re = 0;
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, pw);
			pstmt.setString(2, email);
			pstmt.setInt(3, post_num);
			pstmt.setString(4, address);
			pstmt.setString(5, address2);
			pstmt.setInt(6, phone);
			pstmt.setString(7, message);
			pstmt.setString(8, id);
			
			re = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return re;
	}
	
}
