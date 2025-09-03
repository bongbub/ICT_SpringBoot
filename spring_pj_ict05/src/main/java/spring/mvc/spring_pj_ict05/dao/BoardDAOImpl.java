package spring.mvc.spring_pj_ict05.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.spring_pj_ict05.dto.BoardCommentDTO;
import spring.mvc.spring_pj_ict05.dto.BoardDTO;


@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	
	// 게시글 목록
	@Override
	public List<BoardDTO> boardList(Map<String, Object> map) {
		System.out.println("<<< BoardDAOImpl ==> boardList() >>>");
		
			// 1-1. DB연결
			// 1-2. SQL문 생성
			// 2-1. 데이터가 존재하면
			// 4. List에 dto를 추가한다
		List<BoardDTO> list = sqlSession.selectList("spring.mvc.spring_pj_ict05.dao.BoardDAO.boardList" , map);
		
		
		// 5. list를 리턴
		return list;
	}

	
	// 게시글 갯수 구하기
	@Override
	public int boardCnt(Map<String, Object> params) {
		System.out.println(" <<< BoardDAOImpl - boardCnt() >>>");
		int total = sqlSession.selectOne("spring.mvc.spring_pj_ict05.dao.BoardDAO.boardCnt", params);
		return total;
	}
	

	// 조회수 증가
	@Override
	public void plusReadCnt(int num) {
		System.out.println(" <<< BoardDAOImpl - plusReadCnt() >>>");
		sqlSession.update("spring.mvc.spring_pj_ict05.dao.BoardDAO.plusReadCnt", num);
	}

	// 게시글 상세 처리
	@Override
	public BoardDTO getBoardDetail(int num) {
		System.out.println(" <<< BoardDAOImpl - getBoardDetail() >>>");
		
		BoardDTO dto = new BoardDTO();
		dto = sqlSession.selectOne("spring.mvc.spring_pj_ict05.dao.BoardDAO.getBoardDetail", num);
		return dto;
	}

	// 게시글 수정삭제 버튼 클릭 시 - > 비밀번호 인증 처리
	@Override
	public int password_chk(Map<String, Object> map) {
		System.out.println(" <<< BoardDAOImpl - password_chk() >>>");
		int selectCnt = sqlSession.selectOne("spring.mvc.spring_pj_ict05.dao.BoardDAO.password_chk", map);
		return selectCnt;
	}

	// 게시글 수정 처리
	@Override
	public void updateBoard(BoardDTO dto) {
		System.out.println(" <<< BoardDAOImpl - updateBoard() >>>");
		sqlSession.update("spring.mvc.spring_pj_ict05.dao.BoardDAO.updateBoard",dto);
	}

	// 추가 후 작성한 게시글로 이동
	public int moveRecentBoard(String writer) {
		System.out.println(" <<< BoardDAOImpl - moveRecentBoard() >>>");
		int num = sqlSession.selectOne("spring.mvc.spring_pj_ict05.dao.BoardDAO.moveRecentBoard", writer);
		return num;
	}

	// 게시글 삭제 처리
	@Override
	public void deleteBoard(int num) {
		System.out.println(" <<< BoardDAOImpl - deleteBoard() >>>");
		sqlSession.update("spring.mvc.spring_pj_ict05.dao.BoardDAO.deleteBoard" , num);
	}
	
	
	// 게시글 작성 처리
	@Override
	public void insertBoard(BoardDTO dto) {
		System.out.println(" <<< BoardDAOImpl - insertBoard() >>>");
		sqlSession.insert("spring.mvc.spring_pj_ict05.dao.BoardDAO.insertBoard", dto);
	}
	
	
	// 댓글 작성 처리
	@Override
	public void insertComment(BoardCommentDTO dto) {
		System.out.println(" <<< BoardDAOImpl - insertComment() >>>");
		sqlSession.insert("spring.mvc.spring_pj_ict05.dao.BoardDAO.insertComment",dto);
	}
	
	// 댓글 갯수
	@Override
	public void commentCount(int b_num){
		System.out.println("<<< BoardDAOImpl ==> commentCount() >>>");
		sqlSession.update("spring.mvc.spring_pj_ict05.dao.BoardDAO.commentCount", b_num);
	}
	
	// 댓글 목록 처리
	@Override
	public List<BoardCommentDTO> commentList(int board_num) {
		System.out.println("<<< BoardDAOImpl ==> commentList() >>>");
		// 1. List 생성
		List<BoardCommentDTO> list = new ArrayList<BoardCommentDTO>();
		list = sqlSession.selectList("spring.mvc.spring_pj_ict05.dao.BoardDAO.commentList", board_num);
		// 5. list를 리턴
		return list;
	}
	
}
