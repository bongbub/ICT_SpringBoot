package com.boot.react_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.react_jpa.dto.BoardDTO;
import com.boot.react_jpa.service.BoardServiceImpl;


@CrossOrigin			// 외부에서 자바스크립트 요청이 오는 것을 허용: react와 연동하기 위해 필수! (<= 컨트롤러 진입 직전에 동작)
@RestController		// 일반 @Controller와의 차이 : 
@RequestMapping("/api")
public class BoardController {
	
	
	// RestAPI 규칙
	// 조회 : Get   |  입력 : POST    |    수정 : PUT   |    삭제 : DELETE     + Mapping
	
//	@GetMapping("/")		// localhost:8081/api/
//	public String test() {
//		return "실행성공";
//	}
	
	
	@Autowired
	private BoardServiceImpl service;
	
	
	
	// 게시판 목록		=> @GetMapping  =>  http://localhost:8081/api/boardList
	@GetMapping("/boardList")
	public ResponseEntity<?> findAll() {
		System.out.println(" === boardList ===");
		
		return new ResponseEntity<>(service.boardList(), HttpStatus.OK);		// 리액트와 통신하여 정상접근이면 200 리턴
	}
	
	
	// 게시글 등록  => PostMapping  => http://localhost:8081/api/board
	@PostMapping("/board")
	public ResponseEntity<?> save(@RequestBody BoardDTO board){
		System.out.println(" === save() === ");
		
		return new ResponseEntity<>(service.saveBoard(board), HttpStatus.CREATED);		// 상태코드 Created : 201 리턴
	}
	
	
	// 게시글 상세 => GetMapping  => http://localhost:8081/api/board/{b_num}
	@GetMapping("/board/{b_num}")
	public ResponseEntity<?> findById(@PathVariable int b_num){
		System.out.println(" === findById() === ");
		
		return new ResponseEntity<>(service.findById(b_num), HttpStatus.OK);		// 200 리턴
	}
	

	// 게시글 수정  =>   PutMapping  => http://localhost:8081/api/board/{b_num}
	@PutMapping("/board/{b_num}")
	public ResponseEntity<?> updateBoard(@PathVariable int b_num, @RequestBody BoardDTO dto){
		System.out.println(" === updateBoard() === ");
		
		return new ResponseEntity<>(service.updateBoard(b_num, dto), HttpStatus.OK);
	}
	
	
	
	// 게시글 삭제 => DeleteMapping  => http://localhost:8081/api/board/{b_num}
	@DeleteMapping("/board/{b_num}")
	public ResponseEntity<?> deleteBoard(@PathVariable int b_num){
		System.out.println(" === deleteBoard() === ");
		
		return new ResponseEntity<>(service.deleteBoard(b_num), HttpStatus.OK);		// 200 리턴
	}
	
	
	
	
}
