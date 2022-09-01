package shopping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shopping.vo.ArticleVo;

public class ArticleDao {
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
	
	public ArrayList<ArticleVo> admin_selectAll(int category) {
		ArrayList<ArticleVo> all = new ArrayList<ArticleVo>();
		
		conn = getConnectDB();
		String sql = null;
		if(category<3)
			sql = "select article_no, title, id, regdate, read_cnt, hide from article where category = ?";
		else
			sql = "select article_no, title, id, regdate, read_cnt, hide from notice where category = ?";
		try {
			if(category>2)
				category -= 2;
			
			pstmt = conn.prepareStatement(sql);
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
			sql = "select article_no, title, id, regdate, read_cnt, hide from article"
					+ " where category = ? and "+key+" like ?";
		else
			sql = "select article_no, title, id, regdate, read_cnt, hide from notice"
					+ " where category = ? and "+key+" like ?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			if(category>2)
				category -= 2;
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
}
