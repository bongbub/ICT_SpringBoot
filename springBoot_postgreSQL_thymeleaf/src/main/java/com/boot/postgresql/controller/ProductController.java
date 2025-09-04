package com.boot.postgresql.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.postgresql.dto.ProductDTO;
import com.boot.postgresql.service.ProductServiceImpl;



@Controller
public class ProductController {
	
	@Autowired
	ProductServiceImpl service;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	
	// 실행은 main()함수가 있는 (SpringBootBasicApplication.java)를 우클릭 -> Spring boot APP 클릭
	
	// localhost:8081에 /! --> localhost:8081/
	
	
	// localhost:8081/ => 상품목록
	@RequestMapping("/")
	public String productList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info(" <<< url - productList >>> ");
		
		List<ProductDTO> list = service.listAll();
		
		model.addAttribute("list", list);
		
		return "index";			// 타임리프 템플릿 => src/main/resources 하위의 templates 내 index.html
	}
	
	// 상품 등록 화면
	@RequestMapping("/new")
	public String Product_insertForm(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info(" <<< url -> Product_insertForm >>> ");
		
		return "product_insertForm";
	}
	
	
	// 상품 등록 처리
	@RequestMapping("/save")		
	// dto라는 변수명으로 ProductDTO를 가져와서 Model.addAttribute	--> 이전화면에서 넘어온 값이 setter로 들어가있음
	public String save(@ModelAttribute("dto") ProductDTO dto)
			throws ServletException, IOException{
		logger.info(" <<< url -> save >>> ");
		
		service.save(dto);
		return "redirect:/";			// 1건이 마지막 번호에 추가된 후, product_list.jsp로 이동
	}
	
	// 상세화면
	@RequestMapping("/edit/{id}")		// "@{"/edit/" + ${dto.id}"
	public String updateProductForm(@PathVariable(name="id") int id, Model model)
			throws ServletException, IOException{
		logger.info(" <<< url -> edit >>> ");
		
		ProductDTO dto = service.selectProduct(id);
		model.addAttribute("dto", dto);
		
		return "product_updateForm";	
	}
	
	// 삭제처리				// "@{'/delete/' + ${dto.id}}">Delete
	@RequestMapping("/delete/{id}")		
	public String deleteProductForm(@PathVariable(name="id") int id, Model model)
			throws ServletException, IOException{
		logger.info(" <<< url -> delete >>> ");
		
		service.deleteProduct(id);
		
		return "redirect:/";
	}
	
	
}
