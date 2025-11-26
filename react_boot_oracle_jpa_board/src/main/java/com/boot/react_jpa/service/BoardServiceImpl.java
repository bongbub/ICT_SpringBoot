package com.boot.react_jpa.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.react_jpa.dto.BoardDTO;
import com.boot.react_jpa.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	
	@Autowired
	private BoardRepository boardrepo;			// JPA Repository로 만든 interface -> sql문을 알아서 만들어준다.
	
	// ServiceImpl에서 BoardList호출할 때 @Transactional()추가		 **주의 -> org.springframework.transaction.annotation.Transactional로 주어야함
	
	@Transactional(readOnly=true)		// 서비스 함수가 종료될 때 commit 할지 rollback할지 트랜잭션 관리하겠다.	// readonly : 조회할때만 사용 -> 상세, 목록
	// 게시글 목록
	@Override
	public List<BoardDTO> boardList() {
		System.out.println("BoardService - List");
		
		return boardrepo.findAll();
	}
	
	
	// 게시글 등록
	@Transactional
	@Override
	public BoardDTO saveBoard(BoardDTO dto) {
		System.out.println("BoardService - saveBoard");
		
		return boardrepo.save(dto);		
	}

	
	// 게시글 상세
	@Transactional(readOnly=true)		
	@Override
	public BoardDTO findById(int num) {
		System.out.println("BoardService - findById");
		
		
		return boardrepo.findById(num)
				.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 게시글 번호입니다"));		// findById()르 하다가 생긴 예외를 한 문장으로 처리하는 방법
																									// 실제로 많이 쓰는 방법이니 잘 알아두기
	}

	
	// 게시글 수정
	@Transactional
	@Override
	public BoardDTO updateBoard(int num, BoardDTO dto) {		// BoardDTO로 리턴 : 상세페이지로 리턴하기 위해 
		System.out.println("BoardService - updateBoard");
		
		BoardDTO board = boardrepo.findById(num)
							.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 게시글 번호입니다")); 
		
		board.setB_title(dto.getB_title());
		board.setB_content(dto.getB_content());
		board.setB_password(dto.getB_password());
		board.setB_writer(dto.getB_writer());
		
		return board;
	}
	
	
	// 게시글 삭제
	@Transactional
	@Override
	public String deleteBoard(int num) {
		System.out.println("BoardService - deleteBoard");
		boardrepo.deleteById(num);
		return "ok";
	}
	
	

}
