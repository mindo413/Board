package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;
import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.service.face.BoardService;

public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao = new BoardDaoImpl();

	@Override
	public List<Board> getlist() {

		return boardDao.selectAll();
	}
	
	@Override
	public List<Board> getlist(Paging paging) {
		
		return boardDao.selectAll(paging);
	}

	@Override
	public Board getBoardno(HttpServletRequest req) {
		String param = req.getParameter("boardno");

		int boardno = Integer.parseInt(param);

		Board board = new Board();
		board.setBoardno(boardno);

		return board;
	}

	@Override
	public Board view(Board board) {
		boardDao.updateHit(board);
		return boardDao.selectByBoardno(board);
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		// 요청 파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
//		System.out.println("curPage : " + curPage);
		
		// Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = boardDao.seletCntAll();
		
		// Paging 객체 생성
		Paging paging = new Paging(totalCount,curPage);
		
		return paging;
	}


}
