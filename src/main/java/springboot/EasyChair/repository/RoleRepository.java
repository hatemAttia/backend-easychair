package springboot.EasyChair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.EasyChair.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}