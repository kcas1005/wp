package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.CartVo;

public class CartDao {
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
	
	/*public boolean clearThirdCategoryById(String id) {
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
	}*/
	
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
	//희욱 끝
	
	//두한 시작
	public ArrayList<CartVo> selectCart(String id,int category) {
		ArrayList<CartVo> CartArray = new ArrayList<CartVo>();
		String psql = "select * from cart where id = ? and category = ? order by regdate desc";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			pstmt.setString(1, id);
			pstmt.setInt(2, category);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CartVo temp = new CartVo();
				temp.setId(rs.getString("id"));
				temp.setProduct_no(rs.getInt("product_no"));
				temp.setRegdate(rs.getString("regdate"));
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
	public int check(String id,int proNum,int category) {
		int rec = 0;
		String psql = "update cart set chk = 1 where id = ? and product_no = ? and category = ?";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
	public ArrayList<CartVo> checklist(String id) {
		ArrayList<CartVo> CartArray = new ArrayList<CartVo>();
		String psql = "select * from cart where id = ? and chk = 1 and category = 1";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartVo temp = new CartVo();
				temp.setId(rs.getString("id"));
				temp.setProduct_no(rs.getInt("product_no"));
				temp.setRegdate(rs.getString("regdate"));
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
	public int dirChk(String id , int proNum) {
		int rec = 0;
		String psql = "update cart set chk = 1 where id = ? and product_no = ? and category = 3";
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
	
	public int delseleCart(String id,int proNum,int category) {
		int rec = 0;
		String psql = "delete from cart where id = ? and product_no = ? and category = ? and chk = 1";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
	
	//두한 끝
}
