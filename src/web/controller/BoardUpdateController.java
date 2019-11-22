package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// board DTO객체(요청 파라미터를 얻기위한 객체)
		Board board = boardService.getBoardno(req); // 요청 파라미터 얻기
//		System.out.println("update board : " + board);
		BoardFile boardFile = boardService.getBoardnoByFile(req);
//		System.out.println("update boardfile : " + boardFile);

		// 게시판 상세정도 요청
		Board detailBoard = boardService.view(board);
//		System.out.println("update deetail board : " + detailBoard);
		BoardFile detailBoardFile = boardService.fileview(boardFile);
//		System.out.println("update deetail board file: " + detailBoardFile);

		req.setAttribute("board", detailBoard);
		req.setAttribute("boardfile", detailBoardFile);

		req.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Board board = boardService.getParamForUpdate(req);
		Board board = boardService.update(req,resp);
//		System.out.println("boardno : " + board);
		resp.sendRedirect("/board/view?boardno="+board.getBoardno());
	}
}
