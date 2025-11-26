package fullstack.jwt.back_end.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

//	2-2-2 번째로 타는 클래스
// userAuthProvider.java + UserAuthenticationEntryPoin.java 클래스를 합쳐, SecurityConfig.java  ==> 이 3개의 클래스가 한 묶음이라 볼 수 이씅ㅁ


// 모든 엔드포인트를 구성하기 위해 보안구성
//프론트(react)의, axios_helper.js 클래스에서 넘긴 token 정보를 받아와야함
// OncePerRequestFilter(요청당 한번만 필터하기) 를 상속(extends)를 받아옴

@RequiredArgsConstructor		// 매개변수 생성자
public class JwtAuthFilter extends OncePerRequestFilter{		// 요청당 한번만 사용되길 원하므로

	
	// 매개변수 생성자 안에 들어갈 변수
	private final UserAuthProvider userAuthProvider;			// 2-2-1번째로 타는 클래스(먼저작성)
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		System.out.println(" <<<  JwtAuthFilter.java  --  doFilterInternal()  >>> ");
		
		
		/*리액트 코드 일부 발췌
		onst token = localStorage.getItem("auth_token");
		const headers = token ? { Authorization: `Bearer ${token}` } : {};*/
		
		
		// HttpHeaders -> springframework 거임. import 주의
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(header != null) {	// 길이가 정확하고 Bearer 토큰이어야 한다. 
			String[] elements = header.split(" ");		// 공백을 기준으로 쪼갬
			
			if(elements.length == 2 && "Bearer".equals(elements[0])) {
				try {
					// 자격증명(JWT)이 유효하면, 보안 컨텍스트에 인증빈을 추가한다 ->  검증 통과
					SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validationToken(elements[1]));		// 클래스명.스태틱().setAuth..
				} catch(RuntimeException e) {
					// 에러가 발생하면 클리어
					SecurityContextHolder.clearContext(); 
					throw e;		// 그리고 에러처리
				}
			}
		}
		
		filterChain.doFilter(request, response); 			// 반드시 필터 끝에서 doFilter() 메서드를 호출해라
		
	}		

}
