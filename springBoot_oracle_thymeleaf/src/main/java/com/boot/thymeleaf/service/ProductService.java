package com.boot.thymeleaf.service;

import java.util.List;

import com.boot.thymeleaf.dto.ProductDTO;

public interface ProductService {
	
	// list
	public List<ProductDTO> listAll();
	
	// insert
	public void save(ProductDTO dto);
	
	// 1건 select(상세페이지)
	public ProductDTO selectProduct(int id);

	// delete
	public void deleteProduct(int id);
	
}
