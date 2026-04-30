package com.require4testing.controller;

import com.require4testing.dto.RequirementDTO;
import com.require4testing.enums.Priority;
import com.require4testing.service.RequirementService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/requirements")
public class RequirementController {

    private static final String VIEW = "requirements";
    private static final String REDIRECT = "redirect:/requirements";

    private final RequirementService service;

    public RequirementController(RequirementService service) {
        this.service = service;
    }

    // ===== LIST =====
    @GetMapping
    public String list(Model model) {

        populateModel(model, new RequirementDTO());

        model.addAttribute("activePage", "requirements"); // 🔥 WICHTIG

        return VIEW;
    }

    // ===== SAVE =====
    @PostMapping
    public String save(
            @Valid @ModelAttribute("requirement") RequirementDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            populateModel(model, dto);
            model.addAttribute("activePage", "requirements"); // 🔥 WICHTIG
            return VIEW;
        }

        service.save(dto);

        redirectAttributes.addFlashAttribute("success", "Requirement erfolgreich gespeichert");
        return REDIRECT;
    }

    // ===== DELETE =====
    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes
    ) {

        service.deleteById(id);

        redirectAttributes.addFlashAttribute("success", "Requirement gelöscht");
        return REDIRECT;
    }

    // ===== HELPER =====
    private void populateModel(Model model, RequirementDTO dto) {

        List<RequirementDTO> dtos = service.findAllDTOs();

        model.addAttribute("requirement", dto);
        model.addAttribute("requirements", dtos);
        model.addAttribute("priorities", Priority.values());
    }
}