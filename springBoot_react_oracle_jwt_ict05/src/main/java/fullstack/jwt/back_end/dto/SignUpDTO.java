package fullstack.jwt.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원가입 DTO

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpDTO {

	private String id;
	private char[] password;
	private String first_name;
	private String last_name;
	private String token;
}
