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

import spring.mvc.spring_pj_ict05.service.CustomerServiceImpl;


@Controller
public class CustomerController {
	
	// 로그 출력을 위한
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerServiceImpl service;
	
	// [첫페이지] -----------------------------------
	@RequestMapping("/main.do")
	public String main() {		// return은 차피 jsp주소(String)을 넘겨주므로
		
		logger.info(">>> CustomerController - /main.do <<<");
		
		/* return "/common/main.jsp"; */
		return "common/main";		// servlet-context.xml에 이미 '...views/ '로 슬래시 지정되어있음
									// '.. .jsp' 부분도 지정되어 있기 때문에 생략
	}
	
	// [회원가입] -----------------------------------
	// 회원가입 페이지로 이동
	@RequestMapping("/join.do")
	public String join() {
		logger.info(">>> CustomerController - /join.do <<<");
		return "customer/join/join";
	}
	
	// id 중복확인 버튼 클릭 시
	@RequestMapping("/idConfirmAction.do")
	public String idConfirmAction(HttpServletRequest request, HttpServletResponse response, Model model) 
		throws ServletException, IOException{
		logger.info(">>> CustomerController - /idConfirmAction.do <<<");
		
		service.idConfirmAction(request, response, model);
		
		return "customer/join/idConfirmAction";
	}
	
	// 회원가입 버튼 클릭
	@RequestMapping("/joinAction.do")
	public String joinAction(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException{
		logger.info(">>> CustomerController - /joinAction.do <<<");

		service.signInAction(request, response, model);
		return "customer/join/joinAction";
	}
	
	// [로그인] -----------------------------------
	// 로그인 페이지로 이동
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info(">>> CustomerController - /login.do <<<");

		
		return "customer/login/login";
	}
	
	// 로그인 처리 페이지
	@RequestMapping("/loginAction.do")
	public String loginAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info(">>> CustomerController - /loginAction.do <<<");

		service.loginAction(request, response, model);
		return "customer/login/loginAction";
	}
	
	
	// 로그아웃 처리
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info(">>> CustomerController - /logout.do <<<");
		
		// 세션삭제
		request.getSession().invalidate();
		return "common/main";
	}
	
	
	// [회원탈퇴] -----------------------------------
	// 회원탈퇴 - 인증화면
	@RequestMapping("/deleteCustomer.do")
	public String deleteCustomer(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info(">>> CustomerController - /deleteCustomer.do <<<");
		
		return "customer/mypage/customerinfo/deleteCustomer";
	}
	
	// 회원탈퇴 처리
	@RequestMapping("/deleteCustomerAction.do")
	public String deleteCustomerAction(HttpServletRequest request , HttpServletResponse response, Model model)
			throws ServletException, IOException{
		
		logger.info(">>> CustomerController - /deleteCustomerAction.do <<<");
		
		service.deleteCustomerAction(request, response, model);
		return "customer/mypage/customerinfo/deleteCustomerAction";

	}
	
	
	// [회원 수정] -----------------------------------
	// 회원수정 - 인증화면
	@RequestMapping("/modifyCustomer.do")
	public String modifyCustomer(HttpServletRequest request , HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info(">>> CustomerController - /modifyCustomer.do <<<");
		
		return "customer/mypage/customerinfo/modifyCustomer";
	}
	// 회원수정 - 상세페이지
	@RequestMapping("/modifyDetailAction.do")
	public String modifyDetailAction(HttpServletRequest request , HttpServletResponse response, Model model)
			throws ServletException, IOException{
		
		logger.info(">>> CustomerController - /modifyDetailAction.do <<<");
		service.modifyDetailAction(request, response, model);
		return "customer/mypage/customerinfo/modifyDetailAction";
	}
	
	
	// 회원수정 - 수정처리
	@RequestMapping("/modifyCustomerAction.do")
	public String modifyCustomerAction(HttpServletRequest request , HttpServletResponse response, Model model)
			throws ServletException, IOException{
		logger.info(">>> CustomerController - /modifyDetailAction.do <<<");
		
		service.modifyCustomerAction(request, response, model);
		
		return "customer/mypage/customerinfo/modifyCustomerAction";
	}
}
