package com.boot.springBoot_basic.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.boot.springBoot_basic.dao.ProductDAO;
import com.boot.springBoot_basic.dto.ProductDTO;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO dao;
	
	// list
	@Override
	public void listAll(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("ProductServiceImpl - listAll()");
		
		List<ProductDTO> list = dao.productList();
		model.addAttribute("list", list);
		
	}

	// insert
	@Override
	public void insertProduct(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("ProductServiceImpl - insertProduct()");
		
		ProductDTO dto = new ProductDTO();
		dto.setName(request.getParameter("name"));
		dto.setBrand(request.getParameter("brand"));
		dto.setMadein(request.getParameter("madein"));
		dto.setPrice(Integer.parseInt(request.getParameter("price")));
		dao.insertProduct(dto);
	}

	// update
	@Override
	public void updateProduct(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("ProductServiceImpl - updateProduct()");
		
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDTO dto = new ProductDTO();
		dto.setId(id);
		dto.setName(request.getParameter("name"));
		dto.setBrand(request.getParameter("brand"));
		dto.setMadein(request.getParameter("madein"));
		dto.setPrice(Integer.parseInt(request.getParameter("price")));
		dao.updateProduct(dto);
	}

	// delete
	@Override
	public void deleteProduct(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("ProductServiceImpl - deleteProduct()");
		int id = Integer.parseInt(request.getParameter("id"));
		dao.deleteProduct(id);
	}

	// 1건 select - 상세화면
	@Override
	public void selectProduct(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("ProductServiceImpl - selectProduct()");
		
		// get방식으로 전달햇으므로 id값을 url에서 받아오기
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDTO dto = dao.findById(id);
		System.out.println("서비스 dto : " + dto);
		model.addAttribute("dto", dto);
	}
	
}
