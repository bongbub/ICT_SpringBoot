package fullstack.jwt.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fullstack.jwt.back_end.dto.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, String>{
	
}
