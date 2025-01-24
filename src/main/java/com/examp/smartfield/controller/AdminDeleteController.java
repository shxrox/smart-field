package com.examp.smartfield.controller;

import com.examp.smartfield.model.Delete;
import com.examp.smartfield.service.DeleteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminDeleteController {

    @Autowired
    private DeleteService deleteService;

    // Endpoint for Admin to view the delete records
    @GetMapping("/delete_management")
    public String showAdminDeleteManagement(Model model) {
        List<Delete> deletes = deleteService.getAllDeletes();
        model.addAttribute("deletes", deletes);
        return "admin/delete_management"; // Return the admin template for delete records
    }

    // Add a new delete record
    @PostMapping("/delete_management/add")
    public String addDeleteRecord(@ModelAttribute("delete") @Valid Delete delete) {
        deleteService.saveDelete(delete);
        return "redirect:/admin/delete_management"; // Redirect back to the admin delete management page
    }

}

