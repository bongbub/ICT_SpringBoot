package spring.mvc.spring_pj_ict05.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.spring_pj_ict05.dao.CustomerDAO;
import spring.mvc.spring_pj_ict05.dao.CustomerDAOImpl;
import spring.mvc.spring_pj_ict05.dto.CustomerDTO;


@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDAO dao;		// DAOImpl인데 다형성 적용
	
	// ID 중복 확인 처리
	@Override
	public void idConfirmAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("CustomerServiceImpl - idConfirmAction() ");
		
		// 3단계. 화면에서 입력받은 값 가져오기
		String strId = request.getParameter("user_id");
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		// CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		// 5단계. ID 중복 확인 처리
		int selectCnt = dao.useridCheck(strId);
		
		
		// 6단계. jsp로 처리 결과 전달
		model.addAttribute("selectCnt", selectCnt);	// 결과 전달
		model.addAttribute("strId", strId);
	}
	// 회원가입 처리
	@Override
	public void signInAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		
		System.out.println("CustomerServiceImpl - signInAction()");
		
		// 3단계. 화면에서 입력받은 값 가져와, DTO에 setter로 전달
		
		// DTO생성 -> setter -> 멤버변수로 값 전달
		CustomerDTO dto = new CustomerDTO();
		dto.setUser_id(request.getParameter("user_id"));
		dto.setUser_password(request.getParameter("user_password"));
		dto.setUser_name(request.getParameter("user_name"));
		dto.setUser_birthday(Date.valueOf(request.getParameter("user_birthday")));
//		dto.setUser_address(request.getParameter("user_address"));
		
		String addr1 = request.getParameter("user_address1");	// zipcode
		String addr2 = request.getParameter("user_address2");	// 도로명
		String addr3 = request.getParameter("user_address3");	// 상세줏호
		String addr = addr1 + addr2 + addr3;
		dto.setUser_address(addr);
		System.out.println("최종주소 : " + addr1);
		System.out.println("최종주소 : " + addr2);
		System.out.println("최종주소 : " + addr3);
		System.out.println("최종주소 : " + addr);
		
		
		// hp는 필수가 아니므로 null값이 아닐때만 받아온다 (010 1234 1234)
		String hp = "";
		String hp1 = request.getParameter("user_hp1");
		String hp2 = request.getParameter("user_hp2");
		String hp3 = request.getParameter("user_hp3");
		if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			hp = hp1 +"-" + hp2 + "-" + hp3;
		}
		dto.setUser_hp(hp);
		
		// email란도 체크 -> 필수값이지만 @기준으로 따로 받았으므로
		String email1 = request.getParameter("user_email1");
		String email2 = request.getParameter("user_email2");
		
		String email = email1 + "@" + email2;
		dto.setUser_email(email);
		
		// 등록일 - sysdate가 아니라 만약 직접 넣고 싶다면?
		dto.setUser_regdate(new Timestamp(System.currentTimeMillis()));
		// 위 문장 생략 시, sysdate가 사용됨.
		
		
		/*
		 * // 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용 // CustomerDAOImpl dao = new
		 * CustomerDAOImpl(); CustomerDAO dao = CustomerDAOImpl.getInstance();
		 */
		
		// 5단계. 회원가입 처리
		int insertCnt = dao.insertCustomer(dto);
		
		// 6단계. JSP로 처리 결과를 전달
		model.addAttribute("insertCnt", insertCnt);
		
	}
	
	
	// 로그인 처리 / 회원정보 인증(수정, 탈퇴)
	@Override
	public void loginAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("CustomerServiceImpl - loginAction()");
		
		// 3단계 : 화면에서 입력받은 값을 가져온다
		String strId = request.getParameter("user_id");
		String strPwd = request.getParameter("user_password");
//		// 4단계 : 싱글톤 DAO 호출, 다형성 적용
//		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		// 5단계 : 로그인 처리
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		int loginCnt = dao.idPasswordChk(map);
		
		
		// 6단계 : 로그인 성공시, 세션ID를 설정 ~~~~~~~~ 중요~~~~~~~~~!!
		if(loginCnt == 1) {	// 로그인 성공
			// request.getSession().setAttribute("sessionID", strId); 아래 두 줄과 같은건데 짧게 쓰는 법
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", strId);
		}
		else {
			request.getSession().setAttribute("sessionID", null);
		}
	}

	// 회원정보 인증처리 및 탈퇴처리 
	@Override
	public void deleteCustomerAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("CustomerServiceImpl - deleteCustomerAction()");
		
		String strId = (String)request.getSession().getAttribute("sessionID");
		String strPwd = request.getParameter("user_password");
		
//		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		// 아이디&패스워드 인증
		int selectCnt = dao.idPasswordChk(map);
		int deleteCnt = 0;
		if(selectCnt == 1) {
			deleteCnt = dao.deleteCustomer(strId);	
			System.out.println(deleteCnt);
			if(deleteCnt == 1) {	// 회원이 삭제 되었을 때
				// 세션삭제
				request.getSession().invalidate();
			}
		}
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("deleteCnt", deleteCnt);
		
	}
	
	
	// 회원정보 인증처리 및 상세페이지 조회
	@Override
	public void modifyDetailAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("CustomerServiceImpl - modifyDetailAction()");
		
		String strId = (String)request.getSession().getAttribute("sessionID");
		String strPwd = request.getParameter("user_password");
		
//		CustomerDAO dao = CustomerDAOImpl.getInstance();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		int selectCnt = dao.idPasswordChk(map);
		
		CustomerDTO dto =  null;
		if(selectCnt == 1) {
			dto = dao.getCustomerDetail(strId);
		}
		System.out.println(selectCnt);
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("dto", dto);
	}
	
	
	// 회원정보 수정처리
	@Override
	public void modifyCustomerAction(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ServletException, IOException {
		System.out.println("CustomerServiceImpl - modifyCustomerAction()");
		
		CustomerDTO dto = new CustomerDTO();
		
		dto.setUser_id((String)request.getSession().getAttribute("sessionID"));
		dto.setUser_password(request.getParameter("user_password"));
		dto.setUser_name(request.getParameter("user_name"));
		dto.setUser_birthday(Date.valueOf(request.getParameter("user_birthday")));
		dto.setUser_address(request.getParameter("user_address"));
		
		String hp = "";
		String hp1 =request.getParameter("user_hp1");
		String hp2 = request.getParameter("user_hp2");
		String hp3= request.getParameter("user_hp3");
		if(!hp1.equals("") && !hp2.equals("")&& !hp3.equals("")) {
			hp = hp1 + "-" + hp2 + "-" + hp3;
		}
		dto.setUser_hp(hp);
		
		String eamil1 = request.getParameter("user_email1");
		String email2 = request.getParameter("user_email2");
		String email = eamil1 + "@" + email2;
		dto.setUser_email(email);
		dto.setUser_regdate(new Timestamp(System.currentTimeMillis()));
		
//		CustomerDAO dao = CustomerDAOImpl.getInstance();
		int updateCnt = dao.updateCustomer(dto);
		System.out.println("updateCnt :"+ updateCnt);
		System.out.println(dto);
		request.setAttribute("updateCnt", updateCnt);
	}
}
