package springboot.EasyChair.service;

import java.util.List;

import springboot.EasyChair.dto.UserDto;
import springboot.EasyChair.entity.User;

public interface IserviceUser {


    User getUserById(Long id);

    User updateUser(Long id, UserDto updatedUserDto);

    User activateUser(Long id);

    User desactivateUser(Long id);

    public void saveUser(UserDto userDto);

    public User findUserByEmail(String email);

    public List<UserDto> findAllUsers();
    
    //public User getUserById(Long id);
    
    

}
