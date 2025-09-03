package spring.mvc.spring_pj_ict05.controller;

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

import spring.mvc.spring_pj_ict05.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// [게시글 목록]
	@RequestMapping("/board_list.bc")
	public String board_list(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info("  <<< url  ==>  /board_list.bc  >>>");
		
		String pageNum = request.getParameter("pageNum");     // null이면 Paging이 1로 처리
	    String searchType = request.getParameter("searchType");  // T/C/W/TC
	    String keyword    = request.getParameter("keyword");     // 검색어
	    String sortType = request.getParameter("sortType");		// 정렬 타입 
		
		service.boardListAction(request, response, pageNum, searchType, keyword, sortType, model);
		return "admin/csCenter/board_list";
	}
	
	@RequestMapping("/search_list.bc")
	public String searchList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		String pageNum = request.getParameter("pageNum");     // null이면 Paging이 1로 처리
	    String searchType = request.getParameter("searchType");  // T/C/W/TC
	    String keyword    = request.getParameter("keyword");     // 검색어
	    String sortType = request.getParameter("sortType");		// 정렬 타입 
	    
	    service.boardListAction(request, response,pageNum, searchType, keyword, sortType, model);
	    
	    String viewPage = request.getContextPath()+"/board_list.bc?pageNum="+pageNum+"&searchType="+searchType+"&keyword="+keyword;
	    response.sendRedirect(viewPage);
	    
	    return null;
	}

	// [게시글 상세] 화면
	@RequestMapping("/board_detailAction.bc")
	public String board_detailAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info("  <<< url  ==>  /board_detailAction.bc  >>>");
		
		service.boardDetailAction(request, response, model);
		return "admin/csCenter/board_detailAction";
	}
	
	// [게시글 수정삭제 버튼] 클릭 시 - > 비밀번호 인증 처리
	@RequestMapping("/password_chkAction.bc")
	public String password_chkAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info("  <<< url  ==>  /password_chkAction.bc  >>>");
		int result = service.password_chkAction(request, response, model);
		
		if(result != 0) {		// 인증 성공시
			return "admin/csCenter/board_edit";
		}
		else {			// 인증실패
			System.out.println("비밀번호 불일치");
			int b_num = Integer.parseInt(request.getParameter("hidden_b_num"));
			
			String viewPage = request.getContextPath() + "/board_detailAction.bc?b_num=" + b_num;
			response.sendRedirect(viewPage);
			return null;
					
		}
	}
	
	// [게시글 수정] 처리
	@RequestMapping("/board_updateAction.bc")
	public String board_updateAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info("  <<< url  ==>  /board_updateAction.bc  >>>");
		
		int result = service.boardUpdateAction(request, response, model);
		String viewPage = request.getContextPath()+"/board_detailAction.bc?b_num="+ result;
		response.sendRedirect(viewPage);
		return null;
	}
	
	// [게시글 삭제] 처리
	@RequestMapping("/board_deleteAction.bc")
	public String board_deleteAction(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException{
		logger.info("  <<< url  ==>  /board_deleteAction.bc  >>>");

		service.boardDeleteAction(request, response, model);
		String viewPage = request.getContextPath()+"/board_list.bc";
		response.sendRedirect(viewPage);
		return null;
	}
	
	// [게시글 작성] 화면
	@RequestMapping("/board_insert.bc")
	public String board_insert(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info("   <<< url  ==>   /board_insert.bc  >>> ");
		
		return "admin/csCenter/board_insert";
	}
	
	
	// [게시글 작성] 처리
	@RequestMapping("board_insertAction.bc")
	public String board_insertAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info("   <<< url  ==>   /board_insertAction.bc  >>> ");
		
		int num = service.boardInsertAction(request, response, model);
		String viewPage = request.getContextPath()+"/board_detailAction.bc?b_num="+num;
		response.sendRedirect(viewPage);
		return null;
	}
	
	
	// [댓글 작성] 처리(4)
	@RequestMapping("/comment_insert.bc")
	public String comment_insert(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		logger.info("   <<< url  ==>   /comment_insert.bc  >>> ");
		service.commentAddAction(request, response, model);
//		String viewPage = "admin/csCenter/comment_insert";
//		response.sendRedirect(viewPage);
		return null;
	}
	
	
	// [댓글 목록] 처리  (10)
	@RequestMapping("/comment_list.bc")
	public String comment_list(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info("   <<< url  ==>   /comment_list.bc  >>> ");
		service.commentListAction(request, response, model);
		return "admin/csCenter/comment_list";
	}
	
}
