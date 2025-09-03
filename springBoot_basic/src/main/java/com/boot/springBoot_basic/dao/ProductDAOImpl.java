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
		
		int insertCnt = sqlSession.insert("com.boot.springBoot_basic.dao.ProductDAO.insertProduct", dto);
		return insertCnt;
	}

	// update
	@Override
	public int updateProduct(ProductDTO dto) {
		System.out.println(" DAOImpl - updateProduct() ");
		
		int updateCnt = sqlSession.update("com.boot.springBoot_basic.dao.ProductDAO.updateProduct", dto);
		return updateCnt;
	}

	// delete
	@Override
	public int deleteProduct(int id) {
		System.out.println(" DAOImpl - updateProduct() ");
		int deleteCnt = sqlSession.delete("com.boot.springBoot_basic.dao.ProductDAO.deleteProduct", id);
		return deleteCnt;
	}

	// 1건 select
	@Override
	public ProductDTO findById(int id) {
		System.out.println(" DAOImpl - findById() ");
		
		ProductDTO dto = sqlSession.selectOne("com.boot.springBoot_basic.dao.ProductDAO.findById", id);
		return dto;
	}

}
