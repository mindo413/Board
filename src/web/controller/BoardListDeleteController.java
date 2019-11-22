package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/board/listDelete")
public class BoardListDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] check = req.getParameterValues("checks");

//		System.out.println(check[0]); // 첫번째 요소체크 확인


		for (int i = 0; i < check.length; i++) {
//			System.out.println("삭제 요소 : " + check[i]); // 체크 요소 확인

			Board board = new Board();

			int boardno = Integer.parseInt(check[i]); // 체크된 요소 담기(int형으로)
			board.setBoardno(boardno);

			boardService.delete(board); // 게시글 삭제
		}

		resp.sendRedirect("/board/list");
	}
}
