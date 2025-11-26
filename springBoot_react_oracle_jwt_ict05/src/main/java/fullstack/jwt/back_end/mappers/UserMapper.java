package fullstack.jwt.back_end.mappers;

import org.apache.ibatis.annotations.Mapper;

import fullstack.jwt.back_end.dto.SignUpDTO;
import fullstack.jwt.back_end.dto.UserDTO;

@Mapper
public interface UserMapper {

	
	UserDTO toUserDTO(UserDTO user);
	UserDTO signUpToUser(SignUpDTO userDTO);
}
