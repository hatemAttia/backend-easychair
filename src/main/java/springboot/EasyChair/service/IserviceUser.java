package springboot.EasyChair.service;

import java.util.List;

import springboot.EasyChair.dto.UserDto;
import springboot.EasyChair.entity.User;

public interface IserviceUser {
	
	
	void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

}
