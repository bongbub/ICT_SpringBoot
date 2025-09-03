package spring.mvc.spring_pj_ict05.dao;

import java.util.List;
import java.util.Map;


import spring.mvc.spring_pj_ict05.dto.BoardCommentDTO;
import spring.mvc.spring_pj_ict05.dto.BoardDTO;


public interface BoardDAO {

	// 게시글 목록
	public List<BoardDTO> boardList(Map<String, Object> map);
	
	// 게시글 갯수 구하기
	public int boardCnt(Map<String, Object> params);
	
	// 조회수 증가
	public void plusReadCnt(int num);
	
	// 게시글 상세 처리
	public BoardDTO getBoardDetail(int num);
	
	// 게시글 수정삭제 버튼 클릭 시 - > 비밀번호 인증 처리
	public int password_chk(Map<String, Object> map);

	// 게시글 수정 처리
	public void updateBoard(BoardDTO dto);
	
	// 게시글 삭제 처리
	public void deleteBoard(int num);
	
	// 게시글 작성 처리
	public void insertBoard(BoardDTO dto);
	
	// 작성 후 작성한 게시글로 이동
	public int moveRecentBoard(String writer);
	// 댓글 작성 처리
	public void insertComment(BoardCommentDTO dto);
	
	// 게시글 옆 댓글 카운트
	public void commentCount(int b_num);
	
	// 댓글 목록 처리
	public List<BoardCommentDTO> commentList(int board_num);
}
