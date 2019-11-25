package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.dto.BoardFile;
import web.dto.Recommend;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/board/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// board DTO객체(요청 파라미터를 얻기위한 객체)
		Board board = boardService.getBoardno(req); // 요청 파라미터 얻기
		BoardFile boardFile = boardService.getBoardnoByFile(req);

		// 게시판 상세정도 요청
		Board detailBoard = boardService.view(board);
		BoardFile detailBoardFile = boardService.fileview(boardFile);

		// 추천 상태 전달
		Recommend recommend = new Recommend();
		recommend.setBoardno(board.getBoardno()); // 게시글 번호
		recommend.setUserid((String) req.getSession().getAttribute("userid")); // 로그인한 아이디

		boolean isRecommend = boardService.isRecommend(recommend);
		req.setAttribute("isRecommend", isRecommend);

		req.setAttribute("board", detailBoard);
		req.setAttribute("boardfile", detailBoardFile);

		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(req, resp);
	}
}
