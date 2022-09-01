package shopping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shopping.vo.ProductVo;

public class ProductDao {
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
	
	public boolean regProduct(String name, String img, String dec, String price, String amount, String size) {
		int chk = 0;
		
		conn = getConnectDB();
		String sql = "insert into products(product_name,product_img,product_dec,"
				+ "price,amount,size,regdate)" + 
				"values(?,?,?,?,?,?,now());";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, img);
			pstmt.setString(3, dec);
			pstmt.setInt(4, Integer.valueOf(price));
			pstmt.setInt(5, Integer.valueOf(amount));
			pstmt.setInt(6, Integer.valueOf(size));
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
	
	public String selectNameByNo(int no) {
		String name = null;
		
		conn = getConnectDB();
		String sql = "select product_name from products where product_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			
			rs.next();
			name = rs.getString("product_name");

		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return name;
	}
	
	public int selectPriceByNo(int no) {
		int price = 0;
		
		conn = getConnectDB();
		String sql = "select price from products where product_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			
			rs.next();
			price = rs.getInt("price");

		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return price;
	}
	
	public boolean minusAmount(int no, int purchase) {
		int chk = 0;
		
		conn = getConnectDB();
		String sql = "update products set amount=amount-"+purchase+" where product_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
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
	
	public int amount(int no) {
		int rst = 0;
		
		conn = getConnectDB();
		String sql = "select amount from products where product_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			rs.next();
			rst = rs.getInt("amount");
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return rst;
	}
	
	public ArrayList<ProductVo> admin_selectAll() {
		ArrayList<ProductVo> all = new ArrayList<ProductVo>();
		
		conn = getConnectDB();
		String sql = "select * from products";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVo tmp = new ProductVo();
				
				tmp.setProduct_no(rs.getInt("product_no"));
				tmp.setProduct_name(rs.getString("product_name"));
				tmp.setRegdate(rs.getString("regdate"));
				tmp.setRead_cnt(rs.getInt("read_cnt"));
				tmp.setPrice(rs.getInt("price"));
				tmp.setAmount(rs.getInt("amount"));

				all.add(tmp);
			}
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return all;
	}
	
	public ArrayList<ProductVo> searchArticle(String key, String info) {
		ArrayList<ProductVo> all = new ArrayList<ProductVo>();
		
		conn = getConnectDB();
		
		String sql = "select * from products where "+key+" like ?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+info+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
								
				ProductVo tmp = new ProductVo();
				
				tmp.setProduct_no(rs.getInt("product_no"));
				tmp.setProduct_name(rs.getString("product_name"));
				tmp.setRegdate(rs.getString("regdate"));
				tmp.setRead_cnt(rs.getInt("read_cnt"));
				tmp.setPrice(rs.getInt("price"));
				tmp.setAmount(rs.getInt("amount"));
				
				all.add(tmp);
			}
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return all;
	}
	
	public boolean delProduct(int no) {
		int chk=0;
		
		conn = getConnectDB();
		String sql = "delete from products where product_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
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
	
	public ProductVo selectOneByProductNo(int no) {
		ProductVo pv = new ProductVo();
		
		conn=getConnectDB();
		String sql="select * from products where product_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			rs.next();
			pv.setProduct_name(rs.getString("product_name"));
			pv.setProduct_img(rs.getString("product_img"));
			pv.setProduct_dec(rs.getString("product_dec"));
			pv.setPrice(rs.getInt("price"));
			pv.setAmount(rs.getInt("amount"));
			pv.setSize(rs.getInt("size"));
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return pv;
	}
}
