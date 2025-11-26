package fullstack.jwt.back_end.service;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fullstack.jwt.back_end.dto.CredentialsDTO;
import fullstack.jwt.back_end.dto.SignUpDTO;
import fullstack.jwt.back_end.dto.UserDTO;
import fullstack.jwt.back_end.exception.AppException;
import fullstack.jwt.back_end.mappers.UserMapper;
import fullstack.jwt.back_end.repository.UserRepository;
import lombok.RequiredArgsConstructor;

// 2-2-0 번재 실행되는 클래스
// 굉장히 중요

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	
	public UserDTO findById(String id) {

		System.out.println(" <<< UserService.java  -- findById()  >>>");
		
		UserDTO user = userRepository.findById(id)
				.orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));			// AppException -> 사용자 정의 익셉션. 직접생성함
		
		return userMapper.toUserDTO(user);
	}
	
	// login
	public UserDTO login(CredentialsDTO credentialsDTO) {
		
		System.out.println(" <<< UserService.java  -- login()  >>>");
		
		// 로그인한 정보
		UserDTO user = userRepository.findById(credentialsDTO.getId())		// 로그인 select
			.orElseThrow(() -> new AppException("Unkown user", HttpStatus.NOT_FOUND));
		
		
		// PasswordEncoder 클래스에서 matches() 제공
		// matches 의 역할 => (회원가입시의 비밀번호 === 로그인할때의 비밀번호)를 판단
		if(passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()), user.getPassword())) {			// 이전 비밀번호, 이후 비밀번호
			return user;		// 일치하면 리턴
		}
		
		throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	}
	
	// register
	public UserDTO register(SignUpDTO userDTO) {
		System.out.println(" <<< UserService.java  -- register()  >>>");
		System.out.println(" FirstName : " + userDTO.getFirst_name());
		System.out.println(" LastName : " + userDTO.getLast_name());
		System.out.println(" Id : " + userDTO.getId());
		System.out.println(" Password : " + userDTO.getPassword());
		System.out.println(" Token : " + userDTO.getToken());
		
		// java.util.Optional
		Optional<UserDTO> optionalUser = userRepository.findById(userDTO.getId());
		
		if(optionalUser.isPresent()) {		// 존재하면(Present    <-> isEmpty())
			throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
		}
		
		UserDTO user = new UserDTO();
		user.setId(userDTO.getId());
		user.setFirst_name(userDTO.getFirst_name());
		user.setLast_name(userDTO.getLast_name());
		user.setToken(userDTO.getToken());

		
		// encode() : passwordEncoder를 사용해서 암호를 일반텍스트로 저장하지 않고 해시한다.  ==> 암호화하겟다
		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDTO.getPassword())));
		
		// save
		UserDTO saveUser = userRepository.save(user);
		
		return saveUser;
	}

}
