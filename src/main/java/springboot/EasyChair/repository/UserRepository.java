package springboot.EasyChair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.EasyChair.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
    User findByEmail(String email);

}
