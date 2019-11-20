package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBConn;
import web.dao.face.MemberDao;
import web.dto.Member;

public class MemberDaoImpl implements MemberDao {

	private Connection conn = null; // DB연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL 수행 결과 객체

	@Override
	public int selectCntMemberByUserid(Member member) {

		conn = DBConn.getConnection(); // DB연결

		int cnt = -1;

		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE userid = ? AND userpw = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());

			rs = ps.executeQuery();

			while (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public void insert(Member member) {

		conn = DBConn.getConnection(); // DB연결

		// SQL 쿼리문
		String sql = "";
		sql += "INSERT INTO member(userid, userpw, usernick)";
		sql += " VALUES(?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			// 쿼리채우기
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			ps.setString(3, member.getUsernick());

			ps.executeUpdate(); // SQL 실행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public Member selectMemberByUserid(Member member) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "SELECT userid, userpw, usernick";
		sql += " FROM member";
		sql += " WHERE userid = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getUserid());
			rs = ps.executeQuery();

			while (rs.next()) {
				member.setUserid(rs.getString("userid"));
				member.setUserpw(rs.getString("userpw"));
				member.setUsernick(rs.getString("usernick"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return member;
	}

}
