package com.hk.th.dao;

import java.sql.*;
import com.hk.th.vo.*;

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
	
	public int login(String id , String pw) {
		String psql = "select count(*) as cnt from members where id = ? and pw = ?";
		int re = 0;
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
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
	
	public int selectPoint(String id) {
		String psql = "select point from members where id = ? ";
		int re = 0;
		int point = 0;
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				point = rs.getInt("point");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return point;
	}
	
	public int changePoint(int point,String id) {
		String psql = "update members set point = ? where id = ? ";
		int re = 0;
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setInt(1, point);
			pstmt.setString(2, id);
			
			re = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return re;
	}
	
	public String selectId(String name,int brith) {
		String psql = "select id from members where name = ? and birth = ? ";
		String id = "";
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, name);
			pstmt.setInt(2, brith);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				id = rs.getString("id");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return id;
	}
	
	public int selectPw(String id,String name,int brith) {
		String psql = "select count(*) as cnt from members where id = ? and name = ? and birth = ? ";
		int re = 0;
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, brith);
			
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

	public int changePW(String Pw,String id) {
		String psql = "update members set pw = ? where id = ? ";
		int re = 0;
		con = getConnectDB();
		try {
			pstmt = con.prepareStatement(psql);
			pstmt.setString(1, Pw);
			pstmt.setString(2, id);
			
			re = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return re;
	}
	public boolean slogin(String id, String pw, int lid, int lpw) {
		int chk =0;
		String psql = "select count(*) from members where id = ? and pw = ?";
	      
	    con = getConnectDB();
	    
	    char[] cid = new char[id.length()], cpw = new char[pw.length()];
		for(int i=0;i<id.length();i++) {
			cid[i] = id.charAt(i);
		}
		for(int i=0;i<pw.length();i++) {
			cpw[i] = pw.charAt(i);
		}
	    //복호화
	    char[] reId = new char[lid];
		char[] rePw = new char[lpw];
		for(int i=0; i<lid; i++) {
				reId[i] = cid[i];
			}
		for(int i=0; i<lpw; i++) {
				rePw[i] = cpw[i];
			}
		String nid = new String(reId),
				npw = new String(rePw);
	      try {
	         pstmt = con.prepareStatement(psql);
	         pstmt.setString(1, nid);
	         pstmt.setString(2, npw);
	         
	         rs = pstmt.executeQuery();
	         
	         rs.next();
	            chk = rs.getInt("count(*)");
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      DBClose();
	      
	      if(chk==1)
	    	  return true;
	      else
	    	  return false;
	}
	
}
