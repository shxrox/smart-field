package com.examp.smartfield.controller;

import com.examp.smartfield.model.User;
import com.examp.smartfield.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String registerUser(@ModelAttribute("user") @Valid User user, Model model) {
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

        // Check if the user exists by email
        Optional<User> optionalUser = userService.findUserByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Validate password (in real-world scenarios, use a secure method like bcrypt)
            if (user.getPassword().equals(password)) {

                // Redirect based on user role
                if (user.getRole() == User.Role.FARMER) {
                    return "/farmer/farmer_product_page";  // Redirect to farmer page
                } else if (user.getRole() == User.Role.CUSTOMER) {
                    return "/customer/home";  // Redirect to customer page
                } else if (user.getRole() == User.Role.ADMIN) {
                    return "/admin/dashboard";  // Redirect to admin dashboard
                }

            } else {
                model.addAttribute("error", "Invalid credentials");
            }
        } else {
            model.addAttribute("error", "User not found");
        }

        return "login";
    }

}