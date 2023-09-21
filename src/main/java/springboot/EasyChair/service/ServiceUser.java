package springboot.EasyChair.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springboot.EasyChair.dto.UserDto;
import springboot.EasyChair.entity.Role;
import springboot.EasyChair.entity.User;
import springboot.EasyChair.repository.RoleRepository;
import springboot.EasyChair.repository.UserRepository;

@Service
public class ServiceUser implements IserviceUser {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    public ServiceUser(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(updatedUserDto.getFirstName() + " " + updatedUserDto.getLastName());
            existingUser.setEmail(updatedUserDto.getEmail());
            existingUser.setPhoneNumber(updatedUserDto.getPhoneNumber());
            // ... Mettez à jour d'autres champs si nécessaire
            return userRepository.save(existingUser);
        }
        return null; // Gérer le cas où l'utilisateur n'existe pas
    }
    @Override
    public User activateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setIsActive(true);
            return userRepository.save(user);
        }
        return null; // Gérer le cas où l'utilisateur n'existe pas
    }
    @Override
    public User desactivateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setIsActive(false);
            return userRepository.save(user);
        }
        return null; // Gérer le cas où l'utilisateur n'existe pas
    }
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setIsActive(false);
        user.setDeletedAt(null);
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Collections.singleton(role)); // Use singleton for a single role
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

	

	/*@Override
	public User getUserById(Long id) {
		return userRepository.getById(id);
	}*/

	
	

   
}
