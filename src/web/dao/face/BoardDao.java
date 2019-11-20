package web.dao.face;

import java.util.List;

import util.Paging;
import web.dto.Board;
import web.dto.BoardFile;

public interface BoardDao {

	public List<Board> selectAll();

	public Board selectBoardByBoardno(Board board);

	public void updateHit(Board board);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @return int - 총 게시글 수
	 */
	public int seletCntAll();

	/**
	 * 페이징 대상 게시글 목록 조회
	 * 
	 * @param paging - 페이징 정보
	 * @return List - 조회된 게시글 목록
	 */
	public List<Board> selectAll(Paging paging);

	public void insert(Board board);

	public void insertFile(BoardFile boardFile);
	
	public int selectBoardno();

	public void update(Board board);

	public BoardFile selectBoardFileByBoardno(BoardFile boardFile);
		

}
