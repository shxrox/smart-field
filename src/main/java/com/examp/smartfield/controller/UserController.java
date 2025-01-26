package com.examp.smartfield.controller;

import com.examp.smartfield.model.User;
import com.examp.smartfield.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.examp.smartfield.model.User;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        // Validate passwords match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.user", "Passwords do not match");
        }

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Check if email already exists
        if (userService.findUserByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        userService.registerUser(user);
        model.addAttribute("message", "Registration successful! Please log in.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {

        Optional<User> optionalUser = userService.findUserByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                if (user.getRole() == User.Role.FARMER) {
                    return "/farmer/home";
                } else if (user.getRole() == User.Role.CUSTOMER) {
                    return "/farmer/home";
                } else if (user.getRole() == User.Role.ADMIN) {
                    return "/admin/dashboard";
                }
            } else {
                model.addAttribute("error", "Invalid credentials");
            }
        } else {
            model.addAttribute("error", "User not found");
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
