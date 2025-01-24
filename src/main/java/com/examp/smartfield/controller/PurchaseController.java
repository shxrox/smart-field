package com.examp.smartfield.controller;

import com.examp.smartfield.model.Purchase;
import com.examp.smartfield.model.Product;
import com.examp.smartfield.service.PurchaseService;
import com.examp.smartfield.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public String showPurchaseForm(@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            model.addAttribute("error", "Product not found.");
            return "error"; // Handle errors gracefully
        }
        model.addAttribute("product", product);
        return "farmer/purchase_form"; // Correct template path
    }

    @PostMapping("/{productId}")
    public String processPurchase(
            @PathVariable Long productId,
            @RequestParam("cardNumber") String cardNumber,
            @RequestParam("expiryDate") String expiryDate,
            @RequestParam("cardholderName") String cardholderName,
            @RequestParam("cvv") String cvv,
            Model model) {

        Product product = productService.getProductById(productId);
        if (product == null) {
            model.addAttribute("error", "Invalid product ID.");
            return "errormessage";
        }

        // Save purchase details
        Purchase purchase = new Purchase(productId, cardNumber, expiryDate, cardholderName, product.getPrice());
        purchaseService.savePurchase(purchase);

        model.addAttribute("message", "Purchase successful!");
        return "/farmer/purchase_success";
    }
    @GetMapping("/farmer/payments")
    public String viewPayments(Model model) {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        model.addAttribute("purchases", purchases);
        return "/farmer/payments";
    }
}
