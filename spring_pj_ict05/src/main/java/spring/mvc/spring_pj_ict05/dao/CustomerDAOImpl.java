package spring.mvc.spring_pj_ict05.dao;

import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.spring_pj_ict05.dto.CustomerDTO;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	// 싱글톤 객체 생성  => @Repository -> @Autowired로 시행됨
	
	// 커넥션풀(DBCP : DataBase Connection Pool 방식) - 톰캣파일(Servers)내에있음 context.xml에 설정
	// 커넥션풀이랑 마이바티스 처리할 XML 생성(src/main/resources/-> New Spring Bean Configuration file > dataSource-config.xml 
	// 커넥션풀(context.xml)에 설정 + 마이바티스 경로  ==> dataSource-config.xml 에서 구현
	
	// dataSource-config.xml에서 구현 => 커넥션풀(context.xml에 설정) + 마이바티스 경로
	// Mybatis jar 파일들이 pom.xml에 등록되어 있어야 SqlSession(=인터페이스) 이 import 가능
	// dataSource-config.xml에 bean id="sqlSession" 으로 설정되어있어야함
	
	
	@Autowired
	private SqlSession sqlSession;
	
	
	// ID 중복 확인 처리
	@Override
	public int useridCheck(String strId) {
		int selectCnt = sqlSession.selectOne("spring.mvc.spring_pj_ict05.dao.CustomerDAO.useridCheck", strId);
		return selectCnt;
	}

	
	// 회원가입 처리
	@Override
	public int insertCustomer(CustomerDTO dto) {
		System.out.println("CustomerDAOImpl - insertCustomer() ");
		int insertCnt = sqlSession.insert("spring.mvc.spring_pj_ict05.dao.CustomerDAO.insertCustomer", dto);
			
		return insertCnt ;
	}

	// 로그인 처리 / 회원정보 인증(수정, 탈퇴)
	@Override
	public int idPasswordChk(Map<String, Object> map) {
		System.out.println("CustomerDAOImpl - idPasswordChk() ");
		int loginCnt = sqlSession.selectOne("spring.mvc.spring_pj_ict05.dao.CustomerDAO.idPasswordChk", map);
			
		return loginCnt;
	}

	// 회원정보 인증처리 및 탈퇴처리 
	@Override
	public int deleteCustomer(String strId) {
		System.out.println("CustomerDAOImpl - deleteCustomer() ");
		
		int deleteCnt = sqlSession.delete("spring.mvc.spring_pj_ict05.dao.CustomerDAO.deleteCustomer", strId);
		
		return deleteCnt;
	}

	// 상세페이지 조회
	@Override
	public CustomerDTO getCustomerDetail(String strId) {
		CustomerDTO dto = sqlSession.selectOne("spring.mvc.spring_pj_ict05.dao.CustomerDAO.getCustomerDetail", strId);
		return dto;
	}

	// 회원정보 수정처리
	@Override
	public int updateCustomer(CustomerDTO dto) {
		int updateCnt = sqlSession.update("spring.mvc.spring_pj_ict05.dao.CustomerDAO.updateCustomer", dto);
		return updateCnt;
	}
}
