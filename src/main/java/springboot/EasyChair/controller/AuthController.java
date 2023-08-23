package springboot.EasyChair.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springboot.EasyChair.dto.UserDto;
import springboot.EasyChair.entity.User;
import springboot.EasyChair.service.IserviceUser;

@RestController
@RequestMapping("/api")
@Api(tags = "Authentication API") // Swagger API tags

public class AuthController {
    
    private IserviceUser iserviceUser;

    public AuthController(IserviceUser iserviceUser) {
        this.iserviceUser = iserviceUser;
    }
    
    @GetMapping("/login")
    @ApiOperation(value = "Show login form", notes = "Displays a message indicating that the login form is under construction")
    public ResponseEntity<String> loginForm() {
        return ResponseEntity.ok("Login form is under construction");
    }
    
    @PostMapping("/register")
    @ApiOperation(value = "User registration", notes = "Registers a new user with the provided user information")
    public ResponseEntity<String> registration(@Validated @RequestBody UserDto userDto) {
        User existing = iserviceUser.findUserByEmail(userDto.getEmail());
        if (existing != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is already an account registered with that email");
        }

        iserviceUser.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @GetMapping("/users")
    @ApiOperation(value = "List registered users", notes = "Lists all registered users")
    public ResponseEntity<List<UserDto>> listRegisteredUsers() {
        List<UserDto> users = iserviceUser.findAllUsers();
        return ResponseEntity.ok(users);
    }
}
