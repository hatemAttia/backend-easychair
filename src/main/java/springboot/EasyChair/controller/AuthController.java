package springboot.EasyChair.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springboot.EasyChair.dto.LoginDto;
import springboot.EasyChair.dto.UserDto;
import springboot.EasyChair.entity.User;
import springboot.EasyChair.service.UserService;
import springboot.EasyChair.service.JwtService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/api")
@Api(tags = "Authentication API")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "User login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(jwtToken);
    }
    

    @PostMapping("/register")
    @ApiOperation(value = "User registration")
    public ResponseEntity<String> registration(@Validated @RequestBody UserDto userDto) {
        User existing = userService.findUserByEmail(userDto.getEmail());
        if (existing != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An account with this email already exists");
        }

        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @GetMapping("/users")
    @ApiOperation(value = "List registered users")
    public ResponseEntity<List<User>> listRegisteredUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
}
