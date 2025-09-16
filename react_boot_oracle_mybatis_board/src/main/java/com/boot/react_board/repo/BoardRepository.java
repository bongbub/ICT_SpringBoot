package com.boot.react_board.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.react_board.dto.BoardDTO;

@Repository
public interface BoardRepository extends JpaRepository<BoardDTO, Integer>{
	// 반드시 상속을 받아야 함 -> JpaRepository
}
