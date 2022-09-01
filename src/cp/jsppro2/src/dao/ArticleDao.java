package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ArticleVo;
import vo.ProductVo;

public class ArticleDao {
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
	public ArrayList<ArticleVo> admin_selectAll(int category) {
		ArrayList<ArticleVo> all = new ArrayList<ArticleVo>();
		
		conn = getConnectDB();
		String sql = null;
		if(category<3)
			sql = "select article_no, title, id, regdate, read_cnt, hide, product_no from article where category = ?";
		else
			sql = "select article_no, title, id, regdate, read_cnt, hide from notice where category = ?";
		try {
			
			
			pstmt = conn.prepareStatement(sql);
			if(category>2) {
				pstmt.setInt(1, category-2);
			}
			else
				pstmt.setInt(1, category);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ArticleVo tmp = new ArticleVo();
				
				tmp.setArticle_no(rs.getInt("article_no"));
				tmp.setTitle(rs.getString("title"));
				tmp.setId(rs.getString("id"));
				tmp.setRegdate(rs.getString("regdate"));
				tmp.setRead_cnt(rs.getInt("read_cnt"));
				tmp.setHide(rs.getInt("hide"));
				if(category<3)
					tmp.setProduct_no(rs.getInt("product_no"));
				
				all.add(tmp);
			}
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return all;
	}
	
	public ArrayList<ArticleVo> searchArticle(String key, String info, int category) {
		ArrayList<ArticleVo> all = new ArrayList<ArticleVo>();
		
		String sql = null;
		conn = getConnectDB();
		
		if(category<3)
			sql = "select article_no, title, id, regdate, read_cnt, hide, product_no from article"
					+ " where category = ? and "+key+" like ?";
		else
			sql = "select article_no, title, id, regdate, read_cnt, hide from notice"
					+ " where category = ? and "+key+" like ?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			if(category>2)
				pstmt.setInt(1, category-2);
			pstmt.setInt(1, category);
			pstmt.setString(2, "%"+info+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
								
				ArticleVo tmp = new ArticleVo();
				
				tmp.setArticle_no(rs.getInt("article_no"));
				tmp.setTitle(rs.getString("title"));
				tmp.setId(rs.getString("id"));
				tmp.setRegdate(rs.getString("regdate"));
				tmp.setRead_cnt(rs.getInt("read_cnt"));
				tmp.setHide(rs.getInt("hide"));
				if(category<3)
					tmp.setProduct_no(rs.getInt("product_no"));
				
				all.add(tmp);
			}
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return all;
	}
	
	public boolean hideArticle(int no, int category) {
		int chk = 0;
		String sql=null;
		conn = getConnectDB();
		if(category<3)
			sql = "update article set hide = 1 where article_no = ?";
		else
			sql = "update notice set hide = 1 where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(category>2)
				category -= 2;
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
	
	public boolean showArticle(int no, int category) {
		int chk = 0;
		String sql=null;
		conn = getConnectDB();
		if(category<3)
			sql = "update article set hide = 0 where article_no = ?";
		else
			sql = "update notice set hide = 0 where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(category>2)
				category -= 2;
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
	
	public boolean regArticle(int category, String id, String title, String content, int no, int star) {
		int chk =0;
		
		String sql=null;
		if(category<3)
			sql = "insert into article(category, title, content, id, regdate, product_no, star)"
					+ "values(?,?,?,?,now(),?,?)";
		else
			sql = "insert into notice(category, title, content, id, regdate)"
					+ "values(?,?,?,?,now())";
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(sql);
			if(category<3) {
				pstmt.setInt(1,category);
				pstmt.setInt(5, no);
				
				if(category==1)
					pstmt.setInt(6,star);
				else if(category==2)
					pstmt.setInt(6,0);
			}
			else {
				pstmt.setInt(1, category-2);
			}
			
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, id);
			
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
	
	public boolean reviseArticle(int article_no, int category, String id, String title, String content, int no, int star) {
		int chk =0;
		
		String sql=null;
		if(category<3)
			sql = "update from article set title = ?, content=?, modate=now(),star=? where article_no=?";
		else
			sql = "update from notice set title = ?, content=?, modate=now() where article_no=?";
		
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(sql);
			if(category<3) {
				if(category==1)
					pstmt.setInt(3,star);
				else if(category==2)
					pstmt.setInt(3,0);
			}
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			
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
	
	public boolean delArticle(int category, int no) {
		int chk =0;
		
		String sql=null;
		if(category<3)
			sql = "delete from article where article_no = ?";
		else
			sql = "delete from notice where article_no = ?";
		
		conn = getConnectDB();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
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
	
	public String selectIdByNo(int category, int no) {
		String id = null;
		
		String sql=null;
		if(category<3)
			sql = "select id from article where category = ? and article_no = ?";
		else
			sql = "select id from notice where category = ? and article_no = ?";
		
		conn = getConnectDB();
		try {
			if(category>2)
				category-=2;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category);
			pstmt.setInt(2, no);
			rs = pstmt.executeQuery();
			rs.next();
			id = rs.getString("id");
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return id;
	}
	
	public ArticleVo getArticleInfo(int category,int no) {
		ArticleVo av = new ArticleVo();
		
		conn = getConnectDB();
		String sql = null;
		if(category<3)
			sql = "select * from article where category = ? and article_no = ?";
		else
			sql = "select * from notice where category = ? and article_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			if(category>2)
				pstmt.setInt(1, category-2);
			else
				pstmt.setInt(1, category);
			
			pstmt.setInt(2, no);
			rs=pstmt.executeQuery();
			
			rs.next();
			av.setTitle(rs.getString("title"));
			av.setContent(rs.getString("content"));
			if(category == 1)
				av.setStar(rs.getInt("star"));
			
			
		}catch(Exception e) {
			System.out.println("에러메시지 : "+e.getMessage());
			e.printStackTrace();
		}
		
		DBClose();
		
		return av;
	}
	
	//희욱 끝
	//예진 시작
	
//	리뷰게시판
	
	//전체리스트출력
		public ArrayList<ArticleVo> Review_list(int category, String product_no) {
			conn = getConnectDB(); //DB연결
			ArrayList<ArticleVo> list = new ArrayList<ArticleVo>();
			String sql = "select * from article where category = ? and product_no = ? and hide = 0";
			int rere = 0;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, category);
				pstmt.setString(2, product_no);
				//0 or 1
				rs = pstmt.executeQuery(); // DB의 내용을 출력할때
				while(rs.next()) {
					ArticleVo vo = new ArticleVo();
					vo.setContent(rs.getString("content"));
					vo.setId(rs.getString("id"));
					vo.setRegdate(rs.getString("regdate"));
					vo.setStar(rs.getInt("star"));
					vo.setTitle(rs.getString("title"));
					vo.setArticle_no(rs.getInt("article_no"));
					vo.setCategory(rs.getInt("category"));
					vo.setProduct_no(rs.getInt("product_no"));
					list.add(vo);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBClose(); // DB닫기
			
			return list;
		}
		//전체리스트 페이징출력
				public ArrayList<ArticleVo> Review_page(int category, String product_no, int limit) {
					conn = getConnectDB(); //DB연결
					ArrayList<ArticleVo> list = new ArrayList<ArticleVo>();
					String sql = "select * from article where category = ? and product_no = ?  and hide = 0 order by article_no desc limit ?, 10";
					int rere = 0;
					try {
						pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, category);
						pstmt.setString(2, product_no);
						pstmt.setInt(3, limit);
						//0 or 1
						rs = pstmt.executeQuery(); // DB의 내용을 출력할때
						while(rs.next()) {
							ArticleVo vo = new ArticleVo();
							vo.setContent(rs.getString("content"));
							vo.setId(rs.getString("id"));
							vo.setRegdate(rs.getString("regdate"));
							vo.setStar(rs.getInt("star"));
							vo.setTitle(rs.getString("title"));
							vo.setArticle_no(rs.getInt("article_no"));
							vo.setCategory(rs.getInt("category"));
							vo.setProduct_no(rs.getInt("product_no"));
							vo.setRead_cnt(rs.getInt("read_cnt"));
							vo.setModdate(rs.getString("moddate")); 
							list.add(vo);
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose(); // DB닫기
					
					return list;
				}
				//조회수 올릴 리스트 값 선택 출력
				public ArticleVo select_list( int article_no) {
					conn = getConnectDB(); //DB연결
					ArticleVo vo = new ArticleVo();
					String sql = "select * from article where  article_no = ? ";
					int rere = 0;
					try {
						pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, article_no);
						//0 or 1
						rs = pstmt.executeQuery(); // DB의 내용을 출력할때
						while(rs.next()) {
							vo.setContent(rs.getString("content"));
							vo.setId(rs.getString("id"));
							vo.setRegdate(rs.getString("regdate"));
							vo.setStar(rs.getInt("star"));
							vo.setTitle(rs.getString("title"));
							vo.setArticle_no(rs.getInt("article_no"));
							vo.setCategory(rs.getInt("category"));
							vo.setProduct_no(rs.getInt("product_no"));
							vo.setRead_cnt(rs.getInt("read_cnt"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose(); // DB닫기
					
					return vo;
				}
				//조회수올리기
				public int r_count(int read_cnt, int number) {
					ProductVo temp = new ProductVo();
					String psql = "update article set read_cnt = ? where article_no = ?";
					int rere = 0;
					conn = getConnectDB();
					try {
						pstmt = conn.prepareStatement(psql);
						pstmt.setInt(1, read_cnt);
						pstmt.setInt(2, number);
						
						rere = pstmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose();
					return rere;
				}
				public int review_upload(String review_category, String title, String content,String id, String star, String product_no) {
					String psql = "insert into article(category,title,content,id,star,regdate,product_no)" + 
								"values (? ,?,?,?,?,now(),?)";
					int rere = 0;
					conn = getConnectDB();
					try {
						pstmt = conn.prepareStatement(psql);
						pstmt.setString(1, review_category);
						pstmt.setString(2, title);
						pstmt.setString(3, content);
						pstmt.setString(4, id);
						pstmt.setString(5, star);
						pstmt.setString(6, product_no);
						
						rere = pstmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose();
					return rere;
				}
				//글수정
				public int Revise(String title,String content, String article_no) {
					String psql = "update article set title = ? , content = ? where article_no = ?";
					int rere = 0;
					conn = getConnectDB();
					try {
						pstmt = conn.prepareStatement(psql);
						pstmt.setString(1, title);
						pstmt.setString(2, content);
						pstmt.setString(3, article_no);
						
						rere = pstmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose();
					return rere;
				}
			//수정한 글 나타내기
				public int delete_article(String article_no) {
					
					String psql = "delete from article where article_no = ?";
					int rere = 0;
					conn = getConnectDB();
					try {
						pstmt = conn.prepareStatement(psql);
						pstmt.setString(1, article_no);
						
						rere = pstmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose();
					return rere;
				}
				
				//수정한 글 나타내기
				public int mod(String article_no) {
					String psql = "update article set moddate = now() where article_no = ?";
					int rere = 0;
					conn = getConnectDB();
					try {
						pstmt = conn.prepareStatement(psql);
						pstmt.setString(1, article_no);
						
						rere = pstmt.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose();
					return rere;
				}
				//예진 끝
				
	//두한 시작
				public ArrayList<ArticleVo> selectArticle(String id) {
					ArrayList<ArticleVo> ArticleArray = new ArrayList<ArticleVo>();
					String psql = "select * from article where id = ? order by regdate desc";
					conn = getConnectDB();
					try {
						pstmt = conn.prepareStatement(psql);
						pstmt.setString(1, id);
						
						rs = pstmt.executeQuery();
						while(rs.next()) {
							ArticleVo temp = new ArticleVo();
							temp.setCategory(rs.getInt("category"));
							temp.setProduct_no(rs.getInt("product_no"));
							temp.setTitle(rs.getString("title"));
							temp.setId(rs.getString("id"));
							temp.setRegdate(rs.getString("regdate"));
							temp.setRead_cnt(rs.getInt("read_cnt"));
							ArticleArray.add(temp);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose();
					return ArticleArray;
				}
				
				public int maxMyArticlePage(String id) {
					int rec = 0;
					String psql = "select count(*) as cnt from article where id = ?";
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
				//두한 끝
}
