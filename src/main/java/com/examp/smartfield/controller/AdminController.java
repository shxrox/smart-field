package com.examp.smartfield.controller;

import com.examp.smartfield.model.Crop;
import com.examp.smartfield.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CropService cropService;


    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model) {
        model.addAttribute("pageTitle", "Admin Dashboard");
        return "/admin/dashboard";
    }


    @GetMapping("/cropmanagement")
    public String showCropManagementPage(Model model) {
        List<Crop> crops = cropService.getAllCrops();
        model.addAttribute("crops", crops);
        return "/admin/crop_management";
    }
}
