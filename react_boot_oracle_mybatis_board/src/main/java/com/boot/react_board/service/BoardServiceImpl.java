package com.boot.react_board.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.react_board.dao.BoardMapper;
import com.boot.react_board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService{
	
	
	@Autowired
	private BoardMapper boardMapper;
	
	// ServiceImpl에서 BoardList호출할 때 @Transactional()추가		 **주의 -> org.springframework.transaction.annotation.Transactional로 주어야함
	
	@Transactional(readOnly=true)		// 서비스 함수가 종료될 때 commit 할지 rollback할지 트랜잭션 관리하겠다.	// readonly : 조회할때만 사용 -> 상세, 목록
	// 게시글 목록
	@Override
	public List<BoardDTO> boardList() {
		System.out.println("BoardService - List");
		
		return boardMapper.boardList();
	}
	
	
	// 게시글 등록
	@Transactional
	@Override
	public int saveBoard(BoardDTO dto) {
		System.out.println("BoardService - saveBoard");
		
		return boardMapper.insertBoard(dto);		// 마이바티스 I, U, D 리턴타입이 int(1:성공, 0:실패)
	}

	
	// 게시글 상세
	@Transactional(readOnly=true)		
	@Override
	public BoardDTO findById(int num) {
		System.out.println("BoardService - findById");
		
		return boardMapper.findByNum(num);
	}

	
	// 게시글 수정
	@Transactional
	@Override
	public int updateBoard(int num, BoardDTO dto) {
		System.out.println("BoardService - updateBoard");
		
		return boardMapper.updateBoard(dto);
	}
	
	
	// 게시글 삭제
	@Transactional
	@Override
	public String deleteBoard(int num) {
		System.out.println("BoardService - deleteBoard");
		int cnt = boardMapper.deleteBoard(num);
		return "ok";
	}
	
	

}
