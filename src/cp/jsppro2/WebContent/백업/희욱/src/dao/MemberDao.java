package shopping.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shopping.vo.MemberVo;

import java.util.ArrayList;

public class MemberDao {
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
	
	public ArrayList<MemberVo> admin_selectAll(int includeAdm) {
		ArrayList<MemberVo> all = new ArrayList<MemberVo>();
		
		conn = getConnectDB();
		String sql = "select id, name, email, phone, point, adm from members";
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(includeAdm == 0)//0이면 관리자 제외
					if(rs.getInt("adm") == 1 )
						continue;
				
				MemberVo tmp = new MemberVo();
				
				tmp.setId(rs.getString("id"));
				tmp.setName(rs.getString("name"));
				tmp.setEmail(rs.getString("email"));
				tmp.setPhone(rs.getInt("phone"));
				tmp.setPoint(rs.getInt("point"));
				tmp.setAdm(rs.getInt("adm"));
				
				all.add(tmp);
			}
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return all;
	}
	
	public boolean grantAdmin(String id) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "update members set adm=1 where id = ?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
	
	public boolean removeAdmin(String id) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "update members set adm=0 where id = ?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
	
	public ArrayList<MemberVo> searchMem(String key, String info, int includeAdm) {
		ArrayList<MemberVo> all = new ArrayList<MemberVo>();
		
		conn = getConnectDB();
		String sql = "select id, name, email, phone, point, adm from members where "+key+" like ?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+info+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(includeAdm == 0)//0이면 관리자 제외
					if(rs.getInt("adm") == 1 )
						continue;
				
				MemberVo tmp = new MemberVo();
				
				tmp.setId(rs.getString("id"));
				tmp.setName(rs.getString("name"));
				tmp.setEmail(rs.getString("email"));
				tmp.setPhone(rs.getInt("phone"));
				tmp.setPoint(rs.getInt("point"));
				tmp.setAdm(rs.getInt("adm"));
				
				all.add(tmp);
			}
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return all;
	}
	
	public MemberVo searchMemForPurchase(String id) {
		MemberVo mem = new MemberVo();
		
		conn = getConnectDB();
		String sql="select name,post_num,address,address2,phone,email,message"
				+ " from members where id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			rs.next();
			mem.setName(rs.getString("name"));
			mem.setPost_num(rs.getInt("post_num"));
			mem.setAddress(rs.getString("address"));
			mem.setAddress2(rs.getString("address2"));
			mem.setPhone(rs.getInt("phone"));
			mem.setEmail(rs.getString("email"));
			mem.setMessage(rs.getString("message"));
			
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return mem;
	}
	
	public boolean isMem(String id) {
		int chk = 0;
		
		conn = getConnectDB();
		String sql="select count(*) from members where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			chk = rs.getInt("count(*)");
			
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
	
	public boolean usePoint(String id,int price) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "update members set point=point-"+price+" where id=?";
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
	
	public boolean isAdmin(String id) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "select count(*) from members where adm=1 and id = ?";
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
	
	public boolean isAdmCde(int start1, int start2, int end, int[] inputCde) {

		int[][] code = {{1,2,3,4,5},{2,3,4,5,6},{3,4,5,6,7},{4,5,6,7,8},{5,6,7,8,9}};
		int count=0;
		if(inputCde.length != end)
			return false;
		for(int i=(start2-1); i<(start2+end-1); i++)
			if(code[start1-1][i] != inputCde[count++])
				return false;
		
		return true;
	}
	
	public int login(String id , String pw) {//두한
	      String psql = "select count(*) as cnt from members where id = ? and pw = ?";
	      int re = 0;
	      conn = getConnectDB();
	      try {
	         pstmt = conn.prepareStatement(psql);
	         pstmt.setString(1, id);
	         pstmt.setString(2, pw);
	         
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            re = rs.getInt("cnt");
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      DBClose();
	      return re;
	   }
	
	public boolean slogin(String id, String pw, int lid, int lpw) {
		int chk =0;
		String psql = "select count(*) from members where id = ? and pw = ?";
	      
	    conn = getConnectDB();
	    
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
	         pstmt = conn.prepareStatement(psql);
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
