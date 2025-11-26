package fullstack.jwt.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 로그인시, 비밀번호 인증할때의 DTO


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CredentialsDTO {
	
	private String id;
	private char[] password;
}
