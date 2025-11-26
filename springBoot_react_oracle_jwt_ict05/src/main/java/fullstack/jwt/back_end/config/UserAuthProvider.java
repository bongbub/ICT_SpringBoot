package fullstack.jwt.back_end.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import fullstack.jwt.back_end.dto.UserDTO;
import fullstack.jwt.back_end.service.UserService;
import lombok.RequiredArgsConstructor;


// 2-2-1 번째로 타는 클래스

// JWT를 생성하고 읽으려면 비밀키가 필요하다
// 애플리케이션에서 yml 파일에서 구성하고 여기에 주입한다. 

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
	
	// anotaion의 value를 임포트해야함 주의
	@Value("${security.jwt.token.secret-key:secret-value}")
	private String secretKey;
	private final UserService userService;			// 직접 생성(service패키지>UserService.java(클래스))  // 2-2-0 번재 실행되는 클래스
	
	
	@PostConstruct
	protected void init() {
		System.out.println(" <<<< UserAuthProvider.java  --  init() >>>  ");
		
		// 일단 텍스트로 된 비밀키를 피하기 위해 base64로 인코딩해서 다시 담음
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String id) {
		System.out.println(" <<<< UserAuthProvider.java  --  createToken() >>>  ");
		
		Date now = new Date();			// java.util
		Date validity = new Date(now.getTime() + 3600000);		// 토큰만료시간 설정 (1시간)
		
		// JWT를 사용하려면 pom.xml에 java-jwt 추가해야함
		/*
		<!-- jwt 토큰 생성을 위해 추가(중요) -->
      	<!-- https://mvnrepository.com/artifact/com.auth0/java-jwt -->
      	<dependency>
          	<groupId>com.auth0</groupId>
          	<artifactId>java-jwt</artifactId>
          	<version>3.10.3</version>
      	</dependency>
		 */
		
		
		return JWT.create()
				.withIssuer(id)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.sign(Algorithm.HMAC256(secretKey));		// jwt의 3번째 구조 sign(서명)엔 모든 데이터가 잇어야 하므로 알고리즘 HMAC256 에 secretKey를 들고감
	}
	
	// improt 주의 	org.springframework.security.core.Authentication;
	public Authentication validationToken(String token) {
		System.out.println(" <<<< UserAuthProvider.java  --  validationToken() >>>  ");
		System.out.println(" <<<< UserAuthProvider.java  --  token >>>  " + token);
		
		
		// 임포트 주의 	(import com.auth0.jwt.JWTVerifier;)
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();	
		
		System.out.println(" <<<< UserAuthProvider.java  --  validationToken()  1   >>>  ");
		
		DecodedJWT decoded = verifier.verify(token);		// JWT를 확인하기 위해 먼저 디코딩한다.  유효시간을 초과하면 예외가 발생한다.
		
		
		System.out.println(" <<<< UserAuthProvider.java  --  validationToken()  2   >>>  ");

		UserDTO user = userService.findById(decoded.getIssuer());
		
		
		// 사용자가 데이터베이스에 존재하는지 확인
		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
	
	}
}
