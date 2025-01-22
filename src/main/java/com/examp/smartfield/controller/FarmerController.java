package com.examp.smartfield.controller;

import com.examp.smartfield.model.Product;
import com.examp.smartfield.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/famer")
public class FarmerController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String showProductPage(Model model) {
        // Fetch all products from the service
        model.addAttribute("products", productService.getAllProducts());
        return "/farmer/farmer_product_page"; // The name of the HTML file (farmer_product_page.html)
    }
}
