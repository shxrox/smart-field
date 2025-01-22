package com.examp.smartfield.controller;

import com.examp.smartfield.model.Crop;
import com.examp.smartfield.service.CropService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/farmer/crop")
public class CropController {

    @Autowired
    private CropService cropService;

    @GetMapping
    public String showCropManagementPage(Model model) {
        List<Crop> crops = cropService.getAllCrops();
        model.addAttribute("crops", crops);
        return "/farmer/crop_management";
    }

    @GetMapping("/add")
    public String showAddCropForm(Model model) {
        model.addAttribute("crop", new Crop());
        return "/farmer/add_crop";
    }

    @PostMapping("/add")
    public String addCrop(@ModelAttribute("crop") @Valid Crop crop) {
        cropService.saveCrop(crop);
        return "/farmer/crop_management";
    }

    @GetMapping("/delete/{id}")
    public String deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return "/farmer/crop_management";
    }
}
