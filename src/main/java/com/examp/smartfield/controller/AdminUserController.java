package com.examp.smartfield.controller;

import com.examp.smartfield.model.User;
import com.examp.smartfield.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/manage")
public class AdminUserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String manageUsers(@RequestParam(value = "search", required = false) String search, Model model) {
        List<User> users;
        if (search != null && !search.isEmpty()) {
            users = userService.searchUsersByName(search);
        } else {
            users = userService.getAllUsers();
        }
        model.addAttribute("users", users);
        return "admin/manage_users";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/manage";
    }

}