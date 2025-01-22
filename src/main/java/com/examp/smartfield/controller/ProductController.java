package com.examp.smartfield.controller;

import com.examp.smartfield.model.Product;
import com.examp.smartfield.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getAllProducts());
        return "/admin/add_product";
    }

    @PostMapping
    public String addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("image") MultipartFile imageFile,
            Model model) {

        String imageUrl = "/images/" + imageFile.getOriginalFilename();

        try {
            Path imagePath = Paths.get("src/main/resources/static/images/" + imageFile.getOriginalFilename());
            Files.write(imagePath, imageFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to upload image.");
            return "redirect:/admin/product";
        }

        Product product = new Product(name, description, price, imageUrl);
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("products", productService.getAllProducts());
        return "/admin/add_product";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        Product product = productService.getProductById(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = "/images/" + imageFile.getOriginalFilename();
            try {
                Path imagePath = Paths.get("src/main/resources/static/images/" + imageFile.getOriginalFilename());
                Files.write(imagePath, imageFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setImageUrl(imageUrl);
        }

        productService.saveProduct(product);
        return "redirect:/admin/product";
    }
}