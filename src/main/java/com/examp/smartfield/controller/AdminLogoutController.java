package com.examp.smartfield.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminLogoutController {

    @GetMapping("/logout")
    public String showAboutPage() {
        return "/login";
    }
}





