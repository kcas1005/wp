package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.RecordsVo;

public class RecordsDao {
	static String url="jdbc:mysql://192.168.0.246/jspboard2",
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
	//희욱 시작
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
	//희욱 끝
	//두한 시작
	public ArrayList<RecordsVo> selectOrder(String id) {
		ArrayList<RecordsVo> OrderArray = new ArrayList<RecordsVo>();
		String psql = "select * from records where id = ? order by p_date desc";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RecordsVo temp = new RecordsVo();
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
