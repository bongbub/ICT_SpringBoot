package fullstack.jwt.back_end.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fullstack.jwt.back_end.config.UserAuthProvider;
import fullstack.jwt.back_end.dto.CredentialsDTO;
import fullstack.jwt.back_end.dto.SignUpDTO;
import fullstack.jwt.back_end.dto.UserDTO;
import fullstack.jwt.back_end.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {
	
	// 로거 가져오기 (slf4j로 임포트 해야하는 것 주의)
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	private final UserService userService;
	private final UserAuthProvider userAuthProvider;
	
	// http://localhost:8081    => user / 부트에서 생성한 비밀번호 붙여넣기
	@GetMapping({"", "/"})			
	public String index() {
		
		System.out.println("<<< AuthController.java   --  index() >>>");
		
		return "index";			// 실제 페이지가 아님 => @RestController로 인해, 주소 아닌 값을 브라우저에 출력하는 것임.
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentailsDTO){
		
		System.out.println("<<< AuthController.java   --   login() >>>");
		
		UserDTO user = userService.login(credentailsDTO);
		System.out.println("<<< AuthController.login id=" + user.getId() + " >>>");
		
		System.out.println("token : " + userAuthProvider.createToken(user.getId()));
		user.setToken(userAuthProvider.createToken(user.getId()));		// id를 받아와서 토큰을 새로 생성
		
		return ResponseEntity.ok(user);				// 크롬브라우저 F12 개발자도구 > Headers : 200 OK   => 새로 생성한 JWT(토큰)을 반환함
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody SignUpDTO signUpDTO){
		
		System.out.println("<<< AuthController.java   --   register() >>>");
		
		UserDTO user = userService.register(signUpDTO);			// 등록이므로 리액트에서 넘어온 4개의 정보 + token  => insert
		
		System.out.println("token : " + userAuthProvider.createToken(user.getId()));
		user.setToken(userAuthProvider.createToken(user.getId()));		// id를 받아와서 토큰을 새로 생성
		
		return ResponseEntity.created(URI.create("/users/" + user.getId()))
				.body(user);			// 크롬브라우저 F12 개발자도구 > Headers : 201 OK   => Created 반환
	}
}
