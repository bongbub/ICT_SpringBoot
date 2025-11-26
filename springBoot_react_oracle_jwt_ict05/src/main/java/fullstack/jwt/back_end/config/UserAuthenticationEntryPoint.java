package fullstack.jwt.back_end.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import fullstack.jwt.back_end.dto.ErrorDTO;

// SecurityConfig.java에 사용되는 매개변수 2개 중 1개인 클래스
// 2-1번째로 타는 클래스

// 승인되지 않은 HTTP코드를 반환  	// 예외처리 클래스 

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	// implements AuthenticationEntryPoint를 구현(implements)한 후, 메서드 오버라이드(Add Methods 해줌)

	
	// 참조변수 생성
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	
	@Override
	public void commence(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException authException)throws IOException, ServletException {
		
		
		System.out.println("  <<< UserAuthenticationEntryPoint.java  -- commence()  >>>  ");
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 			// 승인되지 않은 HTTP 코드를 반환한다
		response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);		// 헤더에 json 형식을 사용한다는 뜻
		
		OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDTO("Unauthorized path"));
		
	}
	
}
