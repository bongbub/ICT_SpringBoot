package fullstack.jwt.back_end.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// 조회 DTO

@NoArgsConstructor		// 디폴트 생성자 역할
@AllArgsConstructor		// 매개변수 생성자 역할
@Builder		// lombok  (매개변수 순서에 맞추지 않아도 됨 (ex 매개변수생성자))  
@Data			// Getter, Setter 합쳐서 Data로 줄 수 있음 --> lombok에 있음
@Entity
@Table(name="react_login_tbl")
public class UserDTO {
	
	@Id		// primary key라서
	@Column(name="id")			// 컬럼값과 dto 이름이 매핑되지 않을 때 이렇게 지정해주면 된다. 
	private String id;
	private String password;
	//	 @Column("first_name");		// ==> 별칭 주는 방법 (pom.xml에 추가로 등록해줘야해서 지금은 주석)
	private String first_name;
	private String last_name;
	
	// 토큰은 크기를 굉장히 크게 줘야한다 해쉬코드가 길기 때문에!
	private String token;

}
