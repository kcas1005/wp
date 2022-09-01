package com.hk.th.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ButtonDao {
	static String DB_URL = "jdbc:mysql://localhost/shopping";
	static String DB_ID = "root";
	static String DB_PW = "mysql";
	static Connection conn = null;
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
		if(conn ==null) {
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
		if(conn != null) {
			try {conn.close(); conn = null;} catch (SQLException e) {e.printStackTrace();}
		}
		if(pstmt != null) {
			try {pstmt.close(); pstmt = null;} catch (SQLException e) {e.printStackTrace();}
		}
		if(rs != null) {
			try {rs.close(); rs = null;} catch (SQLException e) {e.printStackTrace();}
		}
	}
	//정보저장
	public int btn_set(String id, String article_no) {
		String psql = "insert into btn(id, article_no) values (? , ?)";
		int rere = 0;
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setString(2, article_no);
			
			rere = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rere;
	}
	public int btn_delete(String id, String article_no) {
		String psql = "delete from btn where id = ? and article_no = ?";
		int rere = 0;
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setString(2, article_no);
			
			rere = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rere;
	}
	public int btn_count(String id, String article_no) {
		String psql = "select count(*) as cnt from btn where id = ? and article_no = ?";
		int rere = 0;
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setString(2, article_no);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rere = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rere;
	}
}
