package fullstack.jwt.back_end.exception;

import org.springframework.http.HttpStatus;

// 사용자 정의 exception
public class AppException extends RuntimeException {
	
	// 멤버변수 정의
	private final HttpStatus code;
	
	// 매개변수 생성자
	public AppException(String message, HttpStatus code) {
		super(message);		// super에 message전달
		this.code = code;
	}
	
	public HttpStatus getCode() {
		return code;
	}
}
