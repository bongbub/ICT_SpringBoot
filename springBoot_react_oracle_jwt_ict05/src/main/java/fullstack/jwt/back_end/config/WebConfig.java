package fullstack.jwt.back_end.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// 1) 첫번째로 타는 클래스
// 프론트엔드가 보낸 요청(==> 토큰, 즉 '자격증명')을 수락(Cors Policy에 의한 NOT ACCESS 해결)

@Configuration
@EnableWebMvc
public class WebConfig {
	
	// org.springframework.web.cors.CorsConfiguration로 임포트!!! 임포트 주의~!!
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	
	
	@Bean			// 빈을 주입해서 객체 생성
	public FilterRegistrationBean corsFilter() {
		
		// 프론트엔드가 보낸 요청(토큰)을 보내면 그것을 받아들여아한다. 
		CorsConfiguration config = new CorsConfiguration();	
		
		System.out.println(" <<< WebConfig - 1 >>> ");
		
		
		// 헤더 관련
		config.setAllowCredentials(true);			// 허용
		config.addAllowedOrigin("http://localhost:3000");
		config.setAllowedHeaders(Arrays.asList(		// 값이 여러개인 헤더를 배열형태로 받아 List로 바꿔줌
				// 주의! springFramework의 Headers로 가져와야 함 꼭!
				HttpHeaders.AUTHORIZATION,
				HttpHeaders.CONTENT_TYPE,
				HttpHeaders.ACCEPT
		));	
		System.out.println(" <<< WebConfig - 2 >>> ");
		
		
		// 각 매핑에 대한 허락 요청
		config.setAllowedMethods(Arrays.asList(
				HttpMethod.GET.name(),
				HttpMethod.POST.name(),
				HttpMethod.PUT.name(),
				HttpMethod.DELETE.name()
		));
		
		config.setMaxAge(3600L);			// 옵션 요청이 수락되는 시간 -> 1시간 설정
		source.registerCorsConfiguration("/**", config);			// 들어오는 모든 url에 위의 작업을 다 적용.
		
		System.out.println(" <<< WebConfig - 3 >>> ");
		
		
		
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));			// source에 위의 정보들이 다 담겨있음 -> 전체 모든 config정보를 모든 사이트에 적용하기 위해 
		bean.setOrder(-102);		// 가장 낮은 위치 - 다른 코드에 영향이 없게 하기 위해서
		
		System.out.println(" <<< WebConfig - 4 >>> ");
		
		return bean;
	}
}
