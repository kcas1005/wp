package com.hk.th.dao;

import java.sql.*;
import java.util.ArrayList;

import com.hk.th.vo.*;

public class NoticeDao {

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
	
	public ArrayList<NoticeVo> selectNotice(int kind) {
		ArrayList<NoticeVo> NoticeArray = new ArrayList<NoticeVo>();
		String psql = "select * from notice where kind = ? order by regdate desc";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setInt(1, kind);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeVo temp = new NoticeVo();
				temp.setArticle_no(rs.getInt("article_no"));
				temp.setContent(rs.getString("content"));
				temp.setId(rs.getString("id"));
				temp.setKind(rs.getInt("kind"));
				temp.setModdate(rs.getInt("moddate"));
				temp.setRead_cnt(rs.getInt("read_cnt"));
				temp.setRegdate(rs.getInt("regdate"));
				temp.setTitle(rs.getString("title"));
				NoticeArray.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return NoticeArray;
	}
	
	public int maxCountNotice(int kind) {
		String psql = "select count(*) as cnt from notice where kind = ? order by regdate desc";
		int re = 0;
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setInt(1, kind);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				re = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return re;
	}
	public NoticeVo selectNoticeIn(int arNum,int kind) {
		NoticeVo temp = new NoticeVo();
		String psql = "select * from notice where article_no = ? and kind = ? order by regdate desc";
		con = getConnectDB();
		
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setInt(1, arNum);
			pstmt.setInt(2, kind);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				temp.setArticle_no(rs.getInt("article_no"));
				temp.setContent(rs.getString("content"));
				temp.setId(rs.getString("id"));
				temp.setKind(rs.getInt("kind"));
				temp.setModdate(rs.getInt("moddate"));
				temp.setRead_cnt(rs.getInt("read_cnt"));
				temp.setRegdate(rs.getInt("regdate"));
				temp.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return temp;
	}
	
	public int pluseRe_cnt(int arNum,int kind) {
		int re = 0;
		String psql = "update notice set read_cnt = read_cnt + 1 where article_no = ? and kind = ?";
		con = getConnectDB();
		
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setInt(1, arNum);
			pstmt.setInt(2, kind);
			
			re = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return re;
	}
}
