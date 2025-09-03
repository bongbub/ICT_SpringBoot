package com.boot.springBoot_basic.dao;

import java.util.List;

import com.boot.springBoot_basic.dto.ProductDTO;

public interface ProductDAO {
	
	// list
	public List<ProductDTO> productList();
	
	
	// insert
	public int insertProduct(ProductDTO dto);
	
	
	// update
	public int updateProduct(ProductDTO dto);
	
	
	// delete
	public int deleteProduct(int id);
	
	
	// 1건 select
	public ProductDTO findById(int id);
	
	

}
