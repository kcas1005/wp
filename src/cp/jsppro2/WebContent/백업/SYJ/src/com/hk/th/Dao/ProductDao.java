package com.hk.th.Dao;
import java.sql.*;
import java.util.*;
import com.hk.th.Vo.*;

import javafx.scene.chart.Chart;

public class ProductDao {

	static String DB_URL = "jdbc:mysql://localhost/shopping";
	static String DB_ID = "root";
	static String DB_PW = "mysql";
	static Connection conn = null;
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
		if(conn ==null) {
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
		if(conn != null) {
			try {conn.close(); conn = null;} catch (SQLException e) {e.printStackTrace();}
		}
		if(pstmt != null) {
			try {pstmt.close(); pstmt = null;} catch (SQLException e) {e.printStackTrace();}
		}
		if(rs != null) {
			try {rs.close(); rs = null;} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public ArrayList<ProductVo> selectProductId(String search) {
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		
		String psql = "select * from products where product_name like '%"+search+"%' order by product_no desc";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			
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
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return list;
	}
	//전체리스트출력
	public ArrayList<ProductVo> All_list() {
		conn = getConnectDB(); //DB연결
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		String sql = "select * from products";
		int rere = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			//0 or 1
			rs = pstmt.executeQuery(); // DB의 내용을 출력할때
			while(rs.next()) {
				ProductVo vo = new ProductVo();
				vo.setProduct_no(rs.getInt("product_no"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setProduct_img(rs.getString("product_img"));
				vo.setProduct_dec(rs.getString("product_dec"));
				vo.setPrice(rs.getInt("price"));
				vo.setAmount(rs.getInt("amount"));
				vo.setSize(rs.getInt("size"));
				vo.setRead_cnt(rs.getInt("read_cnt"));
				vo.setRegdate(rs.getString("regdate"));
				list.add(vo);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose(); // DB닫기
		
		return list;
	}
	
	public ArrayList<ProductVo> main_cnt() {
		conn = getConnectDB(); //DB연결
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		String sql = "select * from products order by product_no desc limit 5";
		int rere = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			//0 or 1
			rs = pstmt.executeQuery(); // DB의 내용을 출력할때
			while(rs.next()) {
				ProductVo vo = new ProductVo();
				vo.setProduct_no(rs.getInt("product_no"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setProduct_img(rs.getString("product_img"));
				vo.setProduct_dec(rs.getString("product_dec"));
				vo.setPrice(rs.getInt("price"));
				vo.setAmount(rs.getInt("amount"));
				vo.setSize(rs.getInt("size"));
				vo.setRead_cnt(rs.getInt("read_cnt"));
				vo.setRegdate(rs.getString("regdate"));
				list.add(vo);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose(); // DB닫기
		
		return list;
	}
	//높은가격순-검색창
	public ArrayList<ProductVo> price_desc4(String search, int offset, int page) {
		conn = getConnectDB(); //DB연결
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		String sql = "select * from products where product_name like '%"+search+"%' order by price desc limit ? , ?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, page);
			//0 or 1
			rs = pstmt.executeQuery(); // DB의 내용을 출력할때
			while(rs.next()) {
				ProductVo vo = new ProductVo();
				vo.setProduct_no(rs.getInt("product_no"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setProduct_img(rs.getString("product_img"));
				vo.setProduct_dec(rs.getString("product_dec"));
				vo.setPrice(rs.getInt("price"));
				vo.setAmount(rs.getInt("amount"));
				vo.setSize(rs.getInt("size"));
				vo.setRead_cnt(rs.getInt("read_cnt"));
				vo.setRegdate(rs.getString("regdate"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose(); // DB닫기
		
		return list;
	}
	//내림가격순-검색창
		public ArrayList<ProductVo> price_asc3(String search, int offset, int page) {
			conn = getConnectDB(); //DB연결
			ArrayList<ProductVo> list = new ArrayList<ProductVo>();
			String sql = "select * from products where product_name like '%"+search+"%' order by price asc limit ? , ?";
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, offset);
				pstmt.setInt(2, page);
				//0 or 1
				rs = pstmt.executeQuery(); // DB의 내용을 출력할때
				while(rs.next()) {
					ProductVo vo = new ProductVo();
					vo.setProduct_no(rs.getInt("product_no"));
					vo.setProduct_name(rs.getString("product_name"));
					vo.setProduct_img(rs.getString("product_img"));
					vo.setProduct_dec(rs.getString("product_dec"));
					vo.setPrice(rs.getInt("price"));
					vo.setAmount(rs.getInt("amount"));
					vo.setSize(rs.getInt("size"));
					vo.setRead_cnt(rs.getInt("read_cnt"));
					vo.setRegdate(rs.getString("regdate"));
					list.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBClose(); // DB닫기
			
			return list;
		}
		//조회 내림차순-검색창
				public ArrayList<ProductVo> read_cnt_dasc2(String search, int offset, int page) {
					conn = getConnectDB(); //DB연결
					ArrayList<ProductVo> list = new ArrayList<ProductVo>();
					String sql = "select * from products where product_name like '%"+search+"%' order by read_cnt desc limit ? , ?";
					try{
						pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, offset);
						pstmt.setInt(2, page);
						//0 or 1
						rs = pstmt.executeQuery(); // DB의 내용을 출력할때
						while(rs.next()) {
							ProductVo vo = new ProductVo();
							vo.setProduct_no(rs.getInt("product_no"));
							vo.setProduct_name(rs.getString("product_name"));
							vo.setProduct_img(rs.getString("product_img"));
							vo.setProduct_dec(rs.getString("product_dec"));
							vo.setPrice(rs.getInt("price"));
							vo.setAmount(rs.getInt("amount"));
							vo.setSize(rs.getInt("size"));
							vo.setRead_cnt(rs.getInt("read_cnt"));
							vo.setRegdate(rs.getString("regdate"));
							list.add(vo);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose(); // DB닫기
					
					return list;
				}
				//전체리스트 기본 페이징
				public ArrayList<ProductVo> list(int offset, int page) {
					conn = getConnectDB(); //DB연결
					ArrayList<ProductVo> list = new ArrayList<ProductVo>();
					String sql = "select * from products ORDER BY product_no DESC limit ? , ?";
					int rere = 0;
					try {
						pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, offset);
						pstmt.setInt(2, page);
						//0 or 1
						rs = pstmt.executeQuery(); // DB의 내용을 출력할때
						while(rs.next()) {
							ProductVo vo = new ProductVo();
							vo.setProduct_no(rs.getInt("product_no"));
							vo.setProduct_name(rs.getString("product_name"));
							vo.setProduct_img(rs.getString("product_img"));
							vo.setProduct_dec(rs.getString("product_dec"));
							vo.setPrice(rs.getInt("price"));
							vo.setAmount(rs.getInt("amount"));
							vo.setSize(rs.getInt("size"));
							vo.setRead_cnt(rs.getInt("read_cnt"));
							vo.setRegdate(rs.getString("regdate"));
							list.add(vo);
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose(); // DB닫기
					
					return list;
				}
				//높은가격순-전체리스트
				public ArrayList<ProductVo> pricelist_desc4(int offset, int page) {
					conn = getConnectDB(); //DB연결
					ArrayList<ProductVo> list = new ArrayList<ProductVo>();
					String sql = "select * from products order BY price desc limit ? , ?";
					try{
						pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, offset);
						pstmt.setInt(2, page);
						
						//0 or 1
						rs = pstmt.executeQuery(); // DB의 내용을 출력할때
						while(rs.next()) {
							ProductVo vo = new ProductVo();
							vo.setProduct_no(rs.getInt("product_no"));
							vo.setProduct_name(rs.getString("product_name"));
							vo.setProduct_img(rs.getString("product_img"));
							vo.setProduct_dec(rs.getString("product_dec"));
							vo.setPrice(rs.getInt("price"));
							vo.setAmount(rs.getInt("amount"));
							vo.setSize(rs.getInt("size"));
							vo.setRead_cnt(rs.getInt("read_cnt"));
							vo.setRegdate(rs.getString("regdate"));
							list.add(vo);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose(); // DB닫기
					
					return list;
				}
				//내림가격순-전체리스트
					public ArrayList<ProductVo> pricelist_asc3(int offset, int page) {
						conn = getConnectDB(); //DB연결
						ArrayList<ProductVo> list = new ArrayList<ProductVo>();
						String sql = "select * from products order BY price asc limit ? , ?";
						try{
							pstmt=conn.prepareStatement(sql);
							pstmt.setInt(1, offset);
							pstmt.setInt(2, page);
							//0 or 1
							rs = pstmt.executeQuery(); // DB의 내용을 출력할때
							while(rs.next()) {
								ProductVo vo = new ProductVo();
								vo.setProduct_no(rs.getInt("product_no"));
								vo.setProduct_name(rs.getString("product_name"));
								vo.setProduct_img(rs.getString("product_img"));
								vo.setProduct_dec(rs.getString("product_dec"));
								vo.setPrice(rs.getInt("price"));
								vo.setAmount(rs.getInt("amount"));
								vo.setSize(rs.getInt("size"));
								vo.setRead_cnt(rs.getInt("read_cnt"));
								vo.setRegdate(rs.getString("regdate"));
								list.add(vo);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						DBClose(); // DB닫기
						
						return list;
					}
					//조회 내림차순
							public ArrayList<ProductVo> read_list_dasc2(int offset, int page) {
								conn = getConnectDB(); //DB연결
								ArrayList<ProductVo> list = new ArrayList<ProductVo>();
								String sql = "select * from products order BY read_cnt desc limit ? , ?";
								try{
									pstmt=conn.prepareStatement(sql);
									pstmt.setInt(1, offset);
									pstmt.setInt(2, page);
									//0 or 1
									rs = pstmt.executeQuery(); // DB의 내용을 출력할때
									while(rs.next()) {
										ProductVo vo = new ProductVo();
										vo.setProduct_no(rs.getInt("product_no"));
										vo.setProduct_name(rs.getString("product_name"));
										vo.setProduct_img(rs.getString("product_img"));
										vo.setProduct_dec(rs.getString("product_dec"));
										vo.setPrice(rs.getInt("price"));
										vo.setAmount(rs.getInt("amount"));
										vo.setSize(rs.getInt("size"));
										vo.setRead_cnt(rs.getInt("read_cnt"));
										vo.setRegdate(rs.getString("regdate"));
										list.add(vo);
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								DBClose(); // DB닫기
								
								return list;
							}
				
	
	
	
	
	
	
	//검색화면 페이징ㅇ
	public ArrayList<ProductVo> search_page(String search, int number, int number2 ) {
		conn = getConnectDB(); //DB연결
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		String sql = "select * from products where product_name like '%"+search+"%' order by product_no desc limit ? , ?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, number2);
			//0 or 1
			rs = pstmt.executeQuery(); // DB의 내용을 출력할때
			while(rs.next()) {
				ProductVo vo = new ProductVo();
				vo.setProduct_no(rs.getInt("product_no"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setProduct_img(rs.getString("product_img"));
				vo.setProduct_dec(rs.getString("product_dec"));
				vo.setPrice(rs.getInt("price"));
				vo.setAmount(rs.getInt("amount"));
				vo.setSize(rs.getInt("size"));
				vo.setRead_cnt(rs.getInt("read_cnt"));
				vo.setRegdate(rs.getString("regdate"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose(); // DB닫기
		
		return list;
	}
	//상세페이지
	public ProductVo content_detail(String number) {
		ProductVo temp = new ProductVo();
		String psql = "select * from products where product_no = ?";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			pstmt.setString(1, number);
			
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
	//조회수 올리기
	public ProductVo r_count(int read_cnt, String product_no) {
		ProductVo temp = new ProductVo();
		int rere = 0;
		String psql = "update products set read_cnt = ? where product_no = ?";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
			pstmt.setInt(1, read_cnt);
			pstmt.setString(2, product_no);
			
			rere = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBClose();
		return temp;
	}
	
	
}
	
