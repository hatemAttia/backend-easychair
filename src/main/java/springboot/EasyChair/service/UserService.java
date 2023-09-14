package springboot.EasyChair.service;

import java.util.List;

import springboot.EasyChair.dto.UserDto;
import springboot.EasyChair.entity.User;

public interface UserService {
	
	
	public void saveUser(UserDto userDto);

    public User findUserByEmail(String email);

    public List<User> findAllUsers();
    
    //public User getUserById(Long id);
    
    

}
