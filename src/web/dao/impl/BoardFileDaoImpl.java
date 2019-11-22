package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBConn;
import web.dao.face.BoardFileDao;
import web.dto.BoardFile;

public class BoardFileDaoImpl implements BoardFileDao {

	private Connection conn = null; // DB연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL 수행 결과 객체

	@Override
	public void delete(BoardFile boardFile) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "DELETE FROM boardfile";
		sql += " WHERE boardno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardFile.getBoardno());

			ps.executeUpdate();

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
	public void insert(BoardFile boardFile) {
		conn = DBConn.getConnection();

		conn = DBConn.getConnection();

		String sql = "";
		sql += "INSERT INTO boardfile(fileno, boardno, originname, storedname, filesize)";
		sql += " VALUES(boardFile_seq.nextval, ?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardFile.getBoardno());
			ps.setString(2, boardFile.getOriginname());
			ps.setString(3, boardFile.getStoredname());
			ps.setInt(4, boardFile.getFilesize());

			ps.executeUpdate();

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

}
