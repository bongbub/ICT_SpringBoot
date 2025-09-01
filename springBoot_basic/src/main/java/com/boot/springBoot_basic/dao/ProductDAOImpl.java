package com.boot.springBoot_basic.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boot.springBoot_basic.dto.ProductDTO;


@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SqlSession sqlSession;

	// list
	@Override
	public List<ProductDTO> productList() {
		System.out.println(" DAOImpl - productList() ");
		
		List<ProductDTO> list = sqlSession.selectList("com.boot.springBoot_basic.dao.ProductDAO.productList");
		System.out.println("list:" + list);
		return list;
	}

	// insert
	@Override
	public int insertProduct(ProductDTO dto) {
		System.out.println(" DAOImpl - insertProduct() ");
		return 0;
	}

	// update
	@Override
	public int updateProduct(ProductDTO dto) {
		System.out.println(" DAOImpl - updateProduct() ");
		return 0;
	}

	// delete
	@Override
	public int updateProduct(int id) {
		System.out.println(" DAOImpl - updateProduct() ");
		return 0;
	}

	// 1건 select
	@Override
	public ProductDTO findById(int id) {
		System.out.println(" DAOImpl - findById() ");
		return null;
	}

}
