package com.hk.th.dao;

import java.sql.*;
import java.util.ArrayList;

import com.hk.th.vo.*;

public class CartDao {

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
	
	public ArrayList<CartVo> selectCart(String id,int category) {
		ArrayList<CartVo> CartArray = new ArrayList<CartVo>();
		String psql = "select * from cart where id = ? and category = ? order by regdate desc";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, category);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CartVo temp = new CartVo();
				temp.setId(rs.getString("id"));
				temp.setProduct_no(rs.getInt("product_no"));
				temp.setRedate(rs.getString("regdate"));
				temp.setCategory(rs.getInt("category"));
				temp.setPurchase(rs.getInt("purchase"));
				CartArray.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return CartArray;
	}
	
	public int maxCartPage(String id,int category) {
		int rec = 0;
		String psql = "select count(*) as cnt from cart where id = ? and category = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, category);
			
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
	
	public int delCart(String id,int proNum,int category) {
		int rec = 0;
		String psql = "delete from cart where id = ? and product_no = ? and category = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, proNum);
			pstmt.setInt(3, category);
			
			rec = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rec;
	}
	
	public int delCartAll(String id,int category) {
		int rec = 0;
		String psql = "delete from cart where id = ? and category = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, category);
			
			rec = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rec;
	}
	
	public int setPur(String id,int proNum,int pur) {
		int rec = 0;
		String psql = "update cart set purchase = ? where id = ? and product_no = ? and category = 1";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setInt(1, pur);
			pstmt.setString(2, id);
			pstmt.setInt(3, proNum);
			
			rec = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rec;
	}
	
	public int setProduct(String id,int proNum,int category) {
		int rec = 0;
		String psql = "insert into cart values(?,?,now(),?,1,0)";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, proNum);
			pstmt.setInt(3, category);
			
			rec = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rec;
	}
	public int check(String id,int proNum) {
		int rec = 0;
		String psql = "update cart set chk = 1 where id = ? and product_no = ? and category = 1";
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
	public ArrayList<CartVo> checklist(String id) {
		ArrayList<CartVo> CartArray = new ArrayList<CartVo>();
		String psql = "select * from cart where id = ? and chk = 1 and category = 1";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartVo temp = new CartVo();
				temp.setId(rs.getString("id"));
				temp.setProduct_no(rs.getInt("product_no"));
				temp.setRedate(rs.getString("regdate"));
				temp.setCategory(rs.getInt("category"));
				temp.setPurchase(rs.getInt("purchase"));
				temp.setChk(rs.getInt("chk"));
				CartArray.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return CartArray;
	}
	
	public int testCheck(String id) {
		int rec = 0;
		String psql = "update cart set chk = 0 where id = ? and category = 1";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			
			rec = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return rec;
	}
	
	public int CheckProduct(String id,int proNum,int categor) {
		int rec = 0;
		String psql = "select count(*) as cnt from cart where id= ? and product_no = ? and category = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, proNum);
			pstmt.setInt(3, categor);
			
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
	
	public int plusProduct(String id,int proNum,int categor) {
		int rec = 0;
		String psql = "select count(*) as cnt from cart where id= ? and product_no = ? and category = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, proNum);
			pstmt.setInt(3, categor);
			
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
