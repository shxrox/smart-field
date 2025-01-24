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
@RequestMapping("/farmer/profile")
public class DeleteController {

    @Autowired
    private DeleteService deleteService;

    @GetMapping
    public String showDeleteManagementPage(Model model) {
        List<Delete> deletes = deleteService.getAllDeletes();
        model.addAttribute("deletes", deletes);
        model.addAttribute("delete", new Delete());
        return "/farmer/delete_account";
    }

    @PostMapping("/add")
    public String saveDelete(@ModelAttribute("delete") @Valid Delete delete) {
        deleteService.saveDelete(delete);
        return "redirect:/farmer/profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        deleteService.deleteById(id);
        return "redirect:/farmer/profile";
    }
}
