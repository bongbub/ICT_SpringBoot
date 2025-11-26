package com.boot.react_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.react_jpa.service.ProductService;


@CrossOrigin
@RestController
@RequestMapping("/pd")
public class ProductController {

	
	@Autowired
	private ProductService service;
	
	// 상품 목록						=> http://localhost:8081/pd/productList
	@GetMapping("/productList")
	public ResponseEntity<?> findAll(){
		System.out.println(" === productList ===");
		
		return new ResponseEntity<>(service.productList(), HttpStatus.OK);
	}
	
	
	// 상품 등록
	
	
	// 상품 상세
	
	
	
	// 상품 수정
	
	
	// 상품 삭제
}
