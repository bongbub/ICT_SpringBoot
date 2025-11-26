package com.boot.react_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.react_jpa.dto.BoardDTO;

@Repository
public interface BoardRepository extends JpaRepository<BoardDTO, Integer>{
	// JPA Repository에서 mapper를 자동 작성
	
}
