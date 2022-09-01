package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ProductVo;

public class ProductDao {
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
	public int maxProductNo() {
		int max = 0;
		
		conn = getConnectDB();
		String sql = "select max(product_no) from products";
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			max = rs.getInt("max(product_no)");
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return max;
	}
	
	public boolean regProduct(String name, String img, String dec, String price, String amount/*, String size*/) {
		int chk = 0;
		
		conn = getConnectDB();
		String sql = "insert into products(product_name,product_img,product_dec,"
				+ "price,amount,regdate)" + 
				"values(?,?,?,?,?,now());";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, img);
			pstmt.setString(3, dec);
			pstmt.setInt(4, Integer.valueOf(price));
			pstmt.setInt(5, Integer.valueOf(amount));
			/*pstmt.setInt(6, Integer.valueOf(size));*/
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
	
	public boolean reviseProduct(String no, String name, String img, String dec, String price, String amount/*, String size*/) {
		int chk = 0;
		
		conn = getConnectDB();
		String sql = "update products set product_name=?,product_img=?,product_dec=?,price=?,amount=?"+
					" where product_no = ?"; 
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, img);
			pstmt.setString(3, dec);
			pstmt.setInt(4, Integer.valueOf(price));
			pstmt.setInt(5, Integer.valueOf(amount));
			/*pstmt.setInt(6, Integer.valueOf(size));*/
			pstmt.setInt(6, Integer.valueOf(no));
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
	//희욱 끝
	//예진 시작
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
		String sql = "select * from products where amount > 0 order by read_cnt desc limit 5";
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
	
	//예진 끝
	//두한 시작
	public ProductVo selectProductId(int id) {
		ProductVo temp = new ProductVo();
		String psql = "select * from products where product_no = ?";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(psql);
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
		conn = getConnectDB();
		int amount = 0;
		try {
			pstmt = conn.prepareStatement(psql);
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
