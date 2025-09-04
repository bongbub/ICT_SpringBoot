package com.boot.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.thymeleaf.dao.ProductRepository;
import com.boot.thymeleaf.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository dao;
	
	@Override
	public List<ProductDTO> listAll() {
		System.out.println("서비스 - listAll()");
		return dao.findAll();	// 모든 list를 조회하겠다
	}

	// insert, update
	@Override
	public void save(ProductDTO dto) {
		System.out.println("서비스 - save()");
		dao.save(dto);		// insert가 있으면 insert, insert가 없으면 update. --> 즉 업데이트에서도 이 문장 그대로 사용 가능.
	}

	// 1건 - select : 상세화면
	@Override
	public ProductDTO selectProduct(int id) {
		System.out.println("서비스 - selectProduct()");
		
		return dao.findById(id).get();
	}

	@Override		
	public void deleteProduct(int id) {		
		System.out.println("서비스 - deleteProduct()");
		dao.deleteById(id);
	}

}
