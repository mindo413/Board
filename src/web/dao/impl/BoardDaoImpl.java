package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConn;
import util.Paging;
import web.dao.face.BoardDao;
import web.dto.Board;
import web.dto.BoardFile;

public class BoardDaoImpl implements BoardDao {

	private Connection conn = null; // DB연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL 수행 결과 객체

	@Override
	public List<Board> selectAll() {

		// DB연결
		conn = DBConn.getConnection();

		// SQL 쿼리
		String sql = "";
		sql += "SELECT boardno, title, id, content, hit, writtendate FROM board";
		sql += " ORDER BY boardno DESC";

		List list = new ArrayList();

		try {
			ps = conn.prepareStatement(sql); // 수행객체 얻기
			rs = ps.executeQuery(); // SQL 수행결과

			// SQL 결과 처리
			while (rs.next()) {
				Board board = new Board(); // DTO Board객체

				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("writtendate"));

				list.add(board);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Board> selectAll(Paging paging) {
		conn = DBConn.getConnection();
//		System.out.println("selectAll" + paging.getSearch());

		String sql = "";

		sql += "select * from(";
		sql += " select rownum rnum, B.* FROM(";
		sql += " select boardno, title, id, content, hit, writtendate from board";
		if (paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += " where title LIKE '%'||'" + paging.getSearch() + "'||'%'";
		}
		sql += " order by boardno desc";
		sql += " )B";
		sql += " ORDER BY rnum";
		sql += " )BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List list = new ArrayList();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();
			while (rs.next()) {
				Board board = new Board();

				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("writtendate"));

				list.add(board);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		return list;
	}

	@Override
	public Board selectBoardByBoardno(Board board) {

		// DB연결
		conn = DBConn.getConnection();

		// SQL 쿼리
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " WHERE boardno = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			rs = ps.executeQuery();

			while (rs.next()) {
				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("writtendate"));
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

		return board;
	}

	@Override
	public void updateHit(Board board) {
		// DB연결
		conn = DBConn.getConnection();

		// SQL 쿼리
		String sql = "";
		sql += "UPDATE board SET hit = hit+1 ";
		sql += " WHERE boardno = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			rs = ps.executeQuery();
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

	}

	@Override
	public int seletCntAll(String search) {
		// DB연결
		conn = DBConn.getConnection();

		// SQL 쿼리
		String sql = "";

		if (search != null) {
			sql += "SELECT count(*) FROM board";
			sql += " WHERE title LIKE ?";
		} else {
			sql += "SELECT count(*) FROM board";
		}

		// 최종 결과 변수
		int cnt = 0;

		try {
			if (search != null) {
				ps = conn.prepareStatement(sql); // 수행객체 얻기
				ps.setString(1, "%" + search + "%");
				rs = ps.executeQuery(); // SQL 수행결과
			} else {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
			}

			// SQL 결과 처리
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

		// 최종 결과 반환
		return cnt;

	}

	@Override
	public void insert(Board board) {

		conn = DBConn.getConnection();

		String sql = "";
		sql += "INSERT INTO board(boardno, title, id, content, hit)";
		sql += " VALUES(?, ?, ?, ?, 0)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getId());
			ps.setString(4, board.getContent());

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
	public void insertFile(BoardFile boardFile) {
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

	@Override
	public int selectBoardno() {
		conn = DBConn.getConnection();

		int boardno = -1;

		String sql = "";
		sql += "SELECT board_seq.nextval FROM dual";

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				boardno = rs.getInt("nextval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardno;
	}

	@Override
	public void update(Board board) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "UPDATE board SET title = ?, content = ?";
		sql += " WHERE boardno = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoardno());

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
	public BoardFile selectBoardFileByBoardno(BoardFile boardFile) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "SELECT * FROM boardfile";
		sql += " WHERE boardno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardFile.getBoardno());

			rs = ps.executeQuery();

			while (rs.next()) {
				boardFile.setBoardno(rs.getInt("boardno"));
				boardFile.setFileno(rs.getInt("fileno"));
				boardFile.setOriginname(rs.getString("originname"));
				boardFile.setStoredname(rs.getString("storedname"));
				boardFile.setFilesize(rs.getInt("filesize"));
				boardFile.setWritedate(rs.getDate("writedate"));
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

		return boardFile;
	}

	@Override
	public void delete(Board board) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "DELETE FROM board";
		sql += " WHERE boardno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno());

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
