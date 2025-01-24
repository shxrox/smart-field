package com.examp.smartfield.controller;


import com.examp.smartfield.model.Purchase;
import com.examp.smartfield.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminViewPayments {

    @Autowired
    private PurchaseService purchaseService;


    @GetMapping("/payments")
    public String viewPayments(Model model) {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        model.addAttribute("purchases", purchases);
        return "admin/payments";
    }
}
