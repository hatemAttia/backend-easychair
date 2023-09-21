package springboot.EasyChair.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springboot.EasyChair.dto.LoginDto;
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
    
    //Login
    
    @PostMapping("/login")
    @ApiOperation(value = "User login", notes = "Logs in a registered user with the provided credentials")
    public ResponseEntity<List<UserDto>> login(@RequestBody LoginDto loginDto) {
        User user = iserviceUser.findUserByEmail(loginDto.getEmail());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Verify the password
        if (!user.getPassword().equals(loginDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        
        List<UserDto> users = iserviceUser.findAllUsers();
        return ResponseEntity.ok(users);
        
        //return ResponseEntity.ok("Login successful");

    }
    
    //registration

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

    
    // List registered users
    
    @GetMapping("/users")
    @ApiOperation(value = "List registered users")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "password", value = "User's password", required = true, dataType = "string", paramType = "query")
    })
    public ResponseEntity<List<UserDto>> listRegisteredUsers(@RequestParam String email, @RequestParam String password) {
        User user = iserviceUser.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Verify the password
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<UserDto> users = iserviceUser.findAllUsers();
        return ResponseEntity.ok(users);
    }

  }

