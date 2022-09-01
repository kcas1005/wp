package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberVo;

public class MemberDao {
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
		String sql="select name,post_num,address,address2,phone,email,message,point"
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
			mem.setPoint(rs.getInt("point"));
			
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
		//암호표 생성
		int[] plus = new int[16];
		for(int i=0; i<16; i++)
			plus[i] = i;
	    //복호화
		for(int i=0;i<id.length();i++) {
			cid[i] = (char)(((int)cid[i])-plus[i]);
			
		}
		for(int i=0;i<pw.length();i++) {
			cpw[i] = (char)(((int)cpw[i])-plus[i]);
		}
		
		lid-= plus[0]; lpw-= plus[0];
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
	//희욱 끝
	//예진 시작
	public MemberVo selectMember(String id) {
		MemberVo memVo = new MemberVo();
		String psql = "select * from members where id = ?";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
	
	
	public int changeInpo(String pw ,String email, int post_num,String address,String address2,int phone,String message, String id) {
		String psql = "update members set pw = ? , email = ?, post_num = ?, address = ? , address2 = ? , phone = ?, message=? where id = ? ";
		int re = 0;
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
	//예진 끝
	// 성진 시작
	public int memberInsert(MemberVo mvo) {
		int rst = 0;
		conn = getConnectDB();
		String sql = "insert into members values(?,?,?,?,?,?,?,?,?,0,0,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getId());
			pstmt.setString(2, mvo.getPw());
			pstmt.setString(3, mvo.getName());
			pstmt.setString(4, mvo.getEmail());
			pstmt.setInt(5, mvo.getPost_num());
			pstmt.setString(6, mvo.getAddress());
			pstmt.setString(7, mvo.getAddress2());
			pstmt.setInt(8, mvo.getBirth());
			pstmt.setInt(9, mvo.getPhone());
			pstmt.setString(10, mvo.getMessage());

			rst = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBClose();
		return rst;
	}

	public int confirmId(String id) throws Exception {// 중복확인 메서드
		int x = -1;
		try {
			conn = getConnectDB();
			pstmt = conn.prepareStatement("SELECT ID FROM MEMBERS WHERE ID = ?"); // id를 입력하고 해당하는 id 가져옴
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			// rs에 저장된 결과가 있으면 if문 실행 없으면 else문 실행
			if (rs.next())
				x = 1; // 해당 아이디 있음
			else
				x = -1; // 해당 아이디 없음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}

		}
		DBClose();
		return x;

	}
	//성진 끝
	//두한 시작
	
	
	public int login(String id , String pw) {
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
	
	public int delMember(String id) {
		String psql = "delete from members where id = ? ";
		int re = 0;
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			pstmt.setString(1, id);
			
			re = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return re;
	}
	
	//두한 끝
}
