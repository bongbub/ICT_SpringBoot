package fullstack.jwt.back_end.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

//2) 두번째로 타는 클래스

// 시큐리티 
@RequiredArgsConstructor			// 매개변수 생성자
@Configuration						// config 부분이니까 넣어줘야하는 어노테이션ㄴ
@EnableWebSecurity					// 시큐리티 작동선언
public class SecurityConfig {

	
	// 매개변수 생성자 지정
	// 작성 => @RequiredArgsConstructordmf 을 선언해주고, 매개변수 앞에 final을 붙여주면 됨. ex) final 변수명
	private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;			// 만들어둔 UserAuthenticationEntryPoint 클래스를 매개변수 중 하나로 선언
	private final UserAuthProvider userAuthProvider;						// 이것도. 만들어뒀던 UserAuthProvider 클래스를 매개변수로 선언
	
	
	
	// 위에 @RequiredArgsConstructordmf를 선언해주지 않았다면 아래와 같이 매개변수 생성자를 선언해줬을 것임.
//	public SecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint, UserAuthProvider userAuthProvider) {
//		this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
//		this.userAuthProvider = userAuthProvider;
//	}
	
	
	// 시큐리티 필터 체인
	// Security Filter Chain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
		throws Exception{
		
		System.out.println(" <<< SecurityConfig.java   --  securityFilterChain()  >>> ");
		
		
		http.exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)				// 보안문제 발생시 사용자 지정 메세지 반환
			.and()
			.addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)	// Spring Sequrity의 인증필터(BasicAuthenticationFilter.class) 전에, JwtAuthFilter부터 사용해라 => Jwt는 토큰이므로, 스프링시큐리티가 먼저 사용되면 접근 자체가 안될 수도 있음. 
			.csrf().disable()		// 시큐리티를 걸면, 각 화면에 csrf를 지정해줘야함. 그것을 비활성화해서 복잡성을 줄이는 작업
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)		// SessionCreationPolicy.STATELESS 애플리케이션을 스프링에 전달하면, 스프링에서 세션과 쿠키를 생성하지 않도록 함. (JWT사용을 위해)
			.and()
			.authorizeHttpRequests((requests) -> requests			// 매개변수 requests를 들고가서 
					.antMatchers(HttpMethod.POST, "/login","/register").permitAll()				//permitAll()-> 모두가 사용가능하게 함. ==> 로그인과 회원가입은 모두가 사용할 수 있어야함 -> 인증이 필요하지 않은 유일한 엔드포인트
					.anyRequest().authenticated()
					);
		return http.build();		//작성한 것들을 가져가서 build시켜라
	}
	
	
}
