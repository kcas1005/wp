package com.hk.th.dao;

import java.sql.*;
import java.util.ArrayList;

import com.hk.th.vo.OrderVo;

public class OrderDao {

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
	
	
	public ArrayList<OrderVo> selectOrder(String id) {
		ArrayList<OrderVo> OrderArray = new ArrayList<OrderVo>();
		String psql = "select * from records where id = ? order by p_date desc";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderVo temp = new OrderVo();
				temp.setId(rs.getString("id"));
				temp.setProduct_no(rs.getInt("product_no"));
				temp.setState(rs.getString("state"));
				temp.setP_date(rs.getString("p_date"));
				OrderArray.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return OrderArray;
	}
	
	public int maxOrderPage(String id) {
		int rec = 0;
		String psql = "select count(*) as cnt from records where id = ?";
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
	
	public String selectCurrentSate(String id,int proNum) {
		String rec = "";
		String psql = "select state from records where id = ? and product_no = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, proNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rec = rs.getString("state");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rec;
	}
	
	public int delOrder(String id,int proNum) {
		int rec = 0;
		String psql = "delete from records where id = ? and product_no = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, proNum);
			
			rec = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rec;
	}
	
}
