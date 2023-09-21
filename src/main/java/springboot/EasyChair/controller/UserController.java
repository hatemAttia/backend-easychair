package springboot.EasyChair.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.EasyChair.dto.UserDto;
import springboot.EasyChair.entity.User;
import springboot.EasyChair.service.IserviceUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User API")
public class UserController {

    private IserviceUser userService;

    public UserController(IserviceUser userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation("Create a new user")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing user")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        User updatedUser = userService.updateUser(id, updatedUserDto);
        if (updatedUser != null) {
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/activate")
    @ApiOperation("Activate a user")
    public ResponseEntity<String> activateUser(@PathVariable Long id) {
        User activatedUser = userService.activateUser(id);
        if (activatedUser != null) {
            return ResponseEntity.ok("User activated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/deactivate")
    @ApiOperation("Deactivate a user")
    public ResponseEntity<String> deactivateUser(@PathVariable Long id) {
        User deactivatedUser = userService.desactivateUser(id);
        if (deactivatedUser != null) {
            return ResponseEntity.ok("User deactivated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a user by ID")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            UserDto userDto = mapToUserDto(user);
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping
//    @ApiOperation("Get all users")
//    public List<UserDto> getAllUsers() {
//        List<UserDto> userDtos = userService.findAllUsers();
//        return userDtos;
//    }

    // Vous pouvez ajouter d'autres méthodes CRUD et de gestion des utilisateurs ici

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        String[] nameParts = user.getName().split(" ");
        userDto.setFirstName(nameParts[0]);
        userDto.setLastName(nameParts[1]);
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        // Vous devez mapper d'autres champs si nécessaire
        return userDto;
    }
}
