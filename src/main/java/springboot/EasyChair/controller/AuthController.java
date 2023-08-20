package springboot.EasyChair.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import springboot.EasyChair.dto.UserDto;
import springboot.EasyChair.entity.User;
import springboot.EasyChair.service.IserviceUser;

@Controller

public class AuthController {
	
	private IserviceUser iserviceUser;

    public AuthController(IserviceUser iserviceUser) {
        this.iserviceUser = iserviceUser;
    }
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    
 // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
 // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = iserviceUser.findUserByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        iserviceUser.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = iserviceUser.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}