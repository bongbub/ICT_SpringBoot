package spring.mvc.spring_pj_ict05.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.spring_pj_ict05.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO{

	// 커넥션 풀, 마이바티스 연동
	@Autowired
	private SqlSession sqlSession;
	
	// 상품 등록
	@Override
	public int productInsert(ProductDTO dto) {
		System.out.println("ProductDAOImpl - productInsert");
		
		// 방법 1)
//		int insertCnt = sqlSession.insert("spring.mvc.spring_pj_ict05.dao.ProductDAO.productInsert", dto);
//		return insertCnt;
		
		// 방법 2) - 메서드를 통한 접근
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);	// 인터페이스(명세서) 호출 -> DAOImpl에서 오버라이드 된 메서드로 호출됨
		int insertCnt = dao.productInsert(dto);		//productInsert를 호출한다는 것은, Mapper의 productInsert를 호출하는 것과 같음
												// 위에 ProductDAO를 호출해주며 -> 인터페이스 호출 ==> 구현화 메서드(DAOImpl) 호출과 같음.
		return insertCnt;
	}

	// 상품 갯수
	@Override
	public int productCnt() {
		System.out.println("ProductDAOImpl - productCnt");
		int selectCnt = sqlSession.selectOne("spring.mvc.spring_pj_ict05.dao.ProductDAO.productCnt");
		return selectCnt;
	}

	// 상품 목록
	@Override
	public List<ProductDTO> productList(Map<String, Object> map) {
		System.out.println("ProductDAOImpl - productList");
		List<ProductDTO> list = sqlSession.selectList("spring.mvc.spring_pj_ict05.dao.ProductDAO.productList", map);
		return list;
	}

	// 상품 상세 페이지
	@Override
	public ProductDTO productDetail(int pdNo) {
		System.out.println("ProductDAOImpl - productDetail");
		
		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
		ProductDTO dto = dao.productDetail(pdNo);
		
		return dto;
	}

	// 상품 수정
	@Override
	public int productUpdate(ProductDTO dto) {
		System.out.println("ProductDAOImpl - productUpdate");
		
//		ProductDAO dao = sqlSession.getMapper(ProductDAO.class);
//		int updateCnt = dao.productUpdate(dto);
		int updateCnt = sqlSession.update("spring.mvc.spring_pj_ict05.dao.ProductDAO.productUpdate", dto);
		
		return updateCnt;
	}

	// 상품 삭제
	@Override
	public int productDelete(int pdNo) {
		System.out.println("ProductDAOImpl - productDelete");
		int deleteCnt = sqlSession.delete("spring.mvc.spring_pj_ict05.dao.ProductDAO.productDelete", pdNo);
		return deleteCnt;
	}

}
