package com.boot.springBoot_basic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.springBoot_basic.service.ProductServiceImpl;

@Controller
public class ProductController {
	
	@Autowired
	ProductServiceImpl service;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	
	// 실행은 main()함수가 있는 (SpringBootBasicApplication.java)를 우클릭 -> Spring boot APP 클릭
	
	// localhost:8081에 /! --> localhost:8081/
	
	
	// 상품 목록 리스트
	@RequestMapping("/list")
	public String productList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		
		logger.info(" <<< url - productList >>> ");
		
		service.listAll(request, response, model);
		
		// src/main 우클릭 -> NEW -> folder => (jsp 플러그인 설치 후 ) other -> jsp 선택
		return "product/product_list";
	}
	
	// 상품 등록 화면
	@RequestMapping("/new")
	public String Product_insertForm(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		
		logger.info(" <<< url -> Product_insertForm >>> ");
		
		return "product/product_insertForm";
	}
	
	
	// 상품 등록 처리
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
				
		logger.info(" <<< url -> save >>> ");
		
		service.insertProduct(request, response, model);
		return "redirect:/list";			// 1건이 마지막 번호에 추가된 후, product_list.jsp로 이동
	}
	
	// 상세화면
	@RequestMapping("/edit")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
				
		logger.info(" <<< url -> edit >>> ");
		service.selectProduct(request, response, model);
		return "product/product_updateForm";	
	}
	
	// 수정 처리
	@RequestMapping("/update")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
				
		logger.info(" <<< url -> update >>> ");
		service.updateProduct(request, response, model);
		return "redirect:/list";
	}
	
	// 삭제처리
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info(" <<< url -> delete >>> ");
		service.deleteProduct(request, response, model);
		return "redirect:/list";
	}
	
	
}
