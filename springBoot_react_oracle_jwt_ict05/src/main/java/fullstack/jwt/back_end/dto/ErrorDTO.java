package fullstack.jwt.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// 에러시 DTO

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorDTO {
	
	private String message;

}
