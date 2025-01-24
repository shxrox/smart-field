package com.examp.smartfield.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/farmer")
public class AboutController {

    @GetMapping("/about")
    public String showAboutPage() {
        return "/farmer/about"; // Return the 'about' view for the About Us page
    }
}
