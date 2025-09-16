package com.boot.react_board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.react_board.dto.BoardDTO;


@Mapper
@Repository
public interface BoardMapper {
	// 원래는 DAO(Interface)를 상속받은 DAOImpl에서 mapper를 호출했었지만, 
	// 그렇지 않아도 바로 호출할 수 있다는 것.
	// @Mapper를 붙여주면 알아서 매퍼와 연동!!
	// DAOImpl에서 붙여주던 @Repository도 붙여줌. --> Repository 역할과 Mapper역할을 모두 수행
	
	
	public List<BoardDTO> boardList();			// 게시글 목록
	
	public int insertBoard(BoardDTO dto);		// 게시글 등록
	
	public int updateBoard(BoardDTO dto);		// 게시글 수정
	
	public int deleteBoard(int b_num);			// 게시글 삭제
	
	public BoardDTO findByNum(int b_num); 		// 게시글 1건 조회
}
