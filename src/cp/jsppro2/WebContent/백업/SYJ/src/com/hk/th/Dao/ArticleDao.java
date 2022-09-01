package com.hk.th.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hk.th.Vo.ArticleVo;
import com.hk.th.Vo.ProductVo;

public class ArticleDao {

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
	
//	리뷰게시판
	
	//전체리스트출력
		public ArrayList<ArticleVo> Review_list(int category, String product_no) {
			conn = getConnectDB(); //DB연결
			ArrayList<ArticleVo> list = new ArrayList<ArticleVo>();
			String sql = "select * from article where category = ? and product_no = ? ";
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
					String sql = "select * from article where category = ? and product_no = ? order by article_no desc limit ?, 10";
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
			
}
