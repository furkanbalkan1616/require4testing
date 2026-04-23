package com.require4testing.controller;

import com.require4testing.entity.Requirement;
import com.require4testing.service.RequirementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequirementController {

    private final RequirementService service;

    public RequirementController(RequirementService service) {
        this.service = service;
    }

    @GetMapping("/requirements")
    public String getRequirements(Model model) {
        model.addAttribute("requirements", service.findAll());
        model.addAttribute("requirement", new Requirement());
        return "requirements";
    }

    @PostMapping("/requirements")
    public String saveRequirement(@ModelAttribute Requirement requirement) {
        service.save(requirement);
        return "redirect:/requirements";
    }
}
