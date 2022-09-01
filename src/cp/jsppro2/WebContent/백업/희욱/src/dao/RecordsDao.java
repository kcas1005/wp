package shopping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordsDao {
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
	
	//입력
	public boolean insert(String id, int product_no) {
		int chk=0;
		conn=getConnectDB();
		String sql = "insert into records values(?,?,'결제완료',now())";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, product_no);
			chk = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		if(chk == 1)
			return true;
		else
			return false;
	}
	
	//배송 완료가 아닌 로우들의 갯수 반환
	public int checkStateByProductNo(int no) {
		int count = 0;
		
		conn=getConnectDB();
		String sql = "select count(*) from records where state!='배송완료' and product_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			rs.next();
			count = rs.getInt("count(*)");
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return count;
	}
}
