package com.examp.smartfield.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {



    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model) {
        model.addAttribute("pageTitle", "Admin Dashboard");
        return "admin_dashboard"; // View for admin dashboard
    }

}