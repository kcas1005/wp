package com.hk.th.dao;

import java.sql.*;
import java.util.ArrayList;

import com.hk.th.vo.*;

public class MyArticleDao {

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
	
	public ArrayList<ArticleVo> selectArticle(String id) {
		ArrayList<ArticleVo> ArticleArray = new ArrayList<ArticleVo>();
		String psql = "select * from article where id = ? order by regdate desc";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ArticleVo temp = new ArticleVo();
				temp.setCategory(rs.getInt("category"));
				temp.setProduct_no(rs.getInt("product_no"));
				temp.setTitle(rs.getString("title"));
				temp.setId(rs.getString("id"));
				temp.setRegdate(rs.getString("regdate"));
				temp.setRead_cnt(rs.getInt("read_cnt"));
				ArticleArray.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return ArticleArray;
	}
	
	public int maxMyArticlePage(String id) {
		int rec = 0;
		String psql = "select count(*) as cnt from article where id = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rec = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rec;
	}
	
}
