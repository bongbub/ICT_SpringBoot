package com.boot.react_jpa.service;

import java.util.List;

import com.boot.react_jpa.dto.ProductDTO;

public interface ProductService {
	
	
	// 상품 목록
	public List<ProductDTO> productList();
	
	
	// 상품 상세
	public ProductDTO findById(int pd_num);
	
	// 상품 등록
	public ProductDTO saveProduct(ProductDTO dto);
	
	// 상품 수정
	public ProductDTO updateProduct(int pd_num, ProductDTO dto);
	
	// 상품 삭제
	public String deleteProduct(int pd_num);
	

}
