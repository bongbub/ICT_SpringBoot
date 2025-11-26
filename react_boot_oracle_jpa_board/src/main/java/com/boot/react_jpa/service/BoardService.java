package com.boot.react_jpa.service;

import java.util.List;

import com.boot.react_jpa.dto.BoardDTO;


public interface BoardService {
	
	// 게시글 목록
	public List<BoardDTO> boardList();
	
	// 게시글 등록
	public BoardDTO saveBoard(BoardDTO dto);
	
	// 게시글 상세
	public BoardDTO findById(int num);
	
	// 게시글 삭제
	public String deleteBoard(int num);
	
	// 게시글 수정
	public BoardDTO updateBoard(int num, BoardDTO dto);
	
}
