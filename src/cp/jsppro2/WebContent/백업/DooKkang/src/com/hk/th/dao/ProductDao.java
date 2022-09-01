package com.hk.th.dao;

import java.sql.*;
import java.util.ArrayList;

import com.hk.th.vo.ProductVo;

public class ProductDao {

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
	
	public ProductVo selectProductId(int id) {
		ProductVo temp = new ProductVo();
		String psql = "select * from products where product_no = ?";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				temp.setProduct_no(rs.getInt("product_no"));
				temp.setProduct_name(rs.getString("product_name"));
				temp.setProduct_img(rs.getString("product_img"));
				temp.setProduct_dec(rs.getString("product_dec"));
				temp.setPrice(rs.getInt("price"));
				temp.setAmount(rs.getInt("amount"));
				temp.setSize(rs.getInt("size"));
				temp.setRead_cnt(rs.getInt("read_cnt"));
				temp.setRegdate(rs.getString("regdate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return temp;
	}
	
	public ArrayList<ProductVo> selectProductAll() {
		ArrayList<ProductVo> arrPdt = new ArrayList<ProductVo>();
		String psql = "select * from products";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVo temp = new ProductVo();
				temp.setProduct_no(rs.getInt("product_no"));
				temp.setProduct_name(rs.getString("product_name"));
				temp.setProduct_img(rs.getString("product_img"));
				temp.setProduct_dec(rs.getString("product_dec"));
				temp.setPrice(rs.getInt("price"));
				temp.setAmount(rs.getInt("amount"));
				temp.setSize(rs.getInt("size"));
				temp.setRead_cnt(rs.getInt("read_cnt"));
				temp.setRegdate(rs.getString("regdate"));
				arrPdt.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return arrPdt;
	}
	
	public int selectAmountPro(int proNum) {
		String psql = "select amount from products where product_no = ?";
		con = getConnectDB();
		int amount = 0;
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setInt(1, proNum);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				amount = rs.getInt("amount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return amount;
	}
}
