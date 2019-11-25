package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Board;
import web.dto.BoardFile;
import web.dto.Recommend;

public interface BoardService {

	/**
	 * 게시글 목록 조회
	 * 
	 * @return List - 게시글 목록
	 */
	public List<Board> getlist();

	/**
	 * 페이징 정보를 활용하여 보여질 게시글 목록만 조회
	 * 
	 * @param pasing - 페이징 정보
	 * @return List - 게시글 목록
	 */
	public List<Board> getlist(Paging paging);

	public Board getBoardno(HttpServletRequest req);

	public Board view(Board board);

	/**
	 * 요청파라미터 curPage를 파싱한다 Board TB와 curPage값을 이용한 Paging 객체를 생성하고 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return Paging - 페이징 정보
	 */
	public Paging getPaging(HttpServletRequest req);

	public Board getParamForUpdate(HttpServletRequest req);

//	public void write(Board board);

	public void write(HttpServletRequest req, HttpServletResponse resp);

	public Board update(HttpServletRequest req, HttpServletResponse resp);

	public BoardFile getBoardnoByFile(HttpServletRequest req);

	public BoardFile fileview(BoardFile boardFile);

	public BoardFile getFile(HttpServletRequest req);

	public void getFile(BoardFile boardFile);

	public void delete(Board board);

	/**
	 * 게시글 추천 상태 조회
	 * 
	 * @param recommend - 추천 상태 체크 객체
	 * @return boolean - true:추천했음, false:추천하지않았음
	 */
	public boolean isRecommend(Recommend recommend);

	/**
	 * 추천 정보 파라미터 얻기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Recommend - 추천 정보 객체
	 */
	public Recommend getRecommend(HttpServletRequest req);

	/**
	 * 추천 토글
	 * 
	 * @param recommend - 추천 정보 객체
	 * @return boolean - true:추천 성공, false:추천취소 성공
	 */
	public boolean recommend(Recommend recommend);

	/**
	 * 게시글의 총 추천 수 구하기
	 * 
	 * @param board - 해당 게시글
	 * @return int - 총 추천 수
	 */
	public int getTotalCntRecommend(Recommend recommend);

//	/**
//	 * 댓글 전달파라미터 꺼내기
//	 */
//	public Comment getComment(HttpServletRequest req);
//
//	/**
//	 * 댓글 입력
//	 * 
//	 * @param comment - 삽입될 댓글
//	 */
//	public void insertComment(Comment comment);
//
//	/**
//	 * 댓글 리스트
//	 * 
//	 * @param board - 댓글이 조회될 게시글
//	 * @return List - 댓글 리스트
//	 */
//	public List getCommentList(Board board);
//
//	/**
//	 * 댓글 삭제
//	 * 
//	 * @param comment - 삭제할 댓글
//	 * @return boolean - 삭제 성공 여부
//	 */
//	public boolean deleteComment(Comment comment);

}
