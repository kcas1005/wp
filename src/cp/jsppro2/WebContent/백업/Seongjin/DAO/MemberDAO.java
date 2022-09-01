package com.hk.th.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hk.th.VO.MemberVO;

public class MemberDAO {

	static String url = "jdbc:mysql://localhost/jdbc", user = "root", password = "mysql";
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnectDB() {
		Connection tempconn = null;

		if (conn == null) {// 공통변수에 접속 정보가 없으면 접속을 만들어 주는 것
			try {
				tempconn = DriverManager.getConnection(url, user, password);
				System.out.println("mysql 접속 성공");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tempconn;
	}

	// 해제 메소드
	public void DBClose() {

		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("mysql DBClose()를 실행하여 접속을 마칩니다");
	}

	public int memberInsert(MemberVO mvo) {
		int rst = 0;
		conn = getConnectDB();
		System.out.println("memberInsert 에러="+conn);
		String sql = "insert into members values (?,?,?,?,?,?,?,?,?,0,0,?)";
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
}
