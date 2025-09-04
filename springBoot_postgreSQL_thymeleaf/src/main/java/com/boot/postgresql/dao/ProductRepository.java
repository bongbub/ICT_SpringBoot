package com.boot.postgresql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.postgresql.dto.ProductDTO;



// pom.xml에 dependency JPA를 반드시 추가해야 사용할 수 있다.  또한 반드시 JPA를 extends 해야함
// JpaRespository(DTO type, Id Type)
public interface ProductRepository extends JpaRepository<ProductDTO, Integer>{
	
}
