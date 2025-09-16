package com.boot.react_board.service;

import java.util.List;

import com.boot.react_board.dto.BoardDTO;

public interface BoardService {
	
	// 게시글 목록
	public List<BoardDTO> boardList();
	
	// 게시글 등록
	public int saveBoard(BoardDTO dto);
	
	// 게시글 상세
	public BoardDTO findById(int num);
	
	// 게시글 삭제
	public String deleteBoard(int num);
	
	// 게시글 수정
	public int updateBoard(int num, BoardDTO dto);
	
}
