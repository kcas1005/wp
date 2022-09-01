package shopping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shopping.vo.CartVo;

public class CartDao {
	static String url="jdbc:mysql://localhost/shopping",
			user = "root",
			password="mysql";
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnectDB() {
		Connection tempconn=null;
		  
		if(conn == null) {//공통변수에 접속 정보가 없으면 접속을 만들어 주는 것
			try {
				tempconn=DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tempconn;
	}
	
	//해재 메소드
	protected void DBClose(){
		
		if(conn!=null) {try{conn.close(); conn=null;}catch(Exception e) {e.printStackTrace();}}
		
		if(pstmt!=null) {try{pstmt.close(); pstmt=null;}catch(Exception e) {e.printStackTrace();}}
		
		if(rs!=null) {try{rs.close(); rs=null;}catch(Exception e) {e.printStackTrace();}}
	}
	
	public ArrayList<CartVo> selectById(String id) {
		ArrayList<CartVo> acv = new ArrayList<CartVo>();
		
		conn = getConnectDB();
		String sql = "select product_no, purchase from cart where id = ? and chk = 1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartVo tmp = new CartVo();
				
				tmp.setProduct_no(rs.getInt("product_no"));
				tmp.setPurchase(rs.getInt("purchase"));
				
				acv.add(tmp);
			}
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return acv;
	}
	
	public boolean clearThirdCategoryById(String id) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "delete from cart where category = 3 and id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			chk = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		if(chk==1)
			return true;
		else
			return false;
	}
	
	public boolean isThirdCategoryById(String id) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "select count(*) from cart where category = 3 and id = ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			rs.next();
			chk = rs.getInt("count(*)");
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		if(chk>0)
			return true;
		else
			return false;
	}
	
	public boolean clearChkById(String id) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "update from cart chk = 0 where category = 1 and chk = 1 and id = ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			chk = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		if(chk==1)
			return true;
		else
			return false;
	}
	
	public boolean isChkExistById(String id) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "select count(*) from cart where category = 1 and chk = 1 and id = ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			rs.next();
			chk = rs.getInt("count(*)");
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		if(chk>0)
			return true;
		else
			return false;
	}
	
	public boolean deletechked(String id, int no) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "delete from cart where id = ? and product_no = ? and chk = 1";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, no);
			chk = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		if(chk==1)
			return true;
		else
			return false;
	}
	
	
}
