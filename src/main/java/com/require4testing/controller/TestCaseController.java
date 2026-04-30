package com.require4testing.controller;

import com.require4testing.dto.TestCaseDTO;
import com.require4testing.service.RequirementService;
import com.require4testing.service.TestCaseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/testcases")
public class TestCaseController {

    private static final Logger log = LoggerFactory.getLogger(TestCaseController.class);

    private static final String VIEW = "testcases";
    private static final String REDIRECT = "redirect:/testcases";

    private final TestCaseService testCaseService;
    private final RequirementService requirementService;

    public TestCaseController(TestCaseService testCaseService,
                              RequirementService requirementService) {
        this.testCaseService = testCaseService;
        this.requirementService = requirementService;
    }

    // ===== LIST =====
    @GetMapping
    public String list(Model model) {

        populateModel(model, new TestCaseDTO());

        model.addAttribute("activePage", "testcases"); // 🔥 FIX

        log.info("TestCase-Seite geladen");

        return VIEW;
    }

    // ===== SAVE =====
    @PostMapping
    public String save(
            @Valid @ModelAttribute("testcase") TestCaseDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            log.warn("Validation Fehler beim TestCase speichern");
            populateModel(model, dto);
            model.addAttribute("activePage", "testcases"); // 🔥 FIX
            return VIEW;
        }

        testCaseService.save(dto);

        log.info("TestCase gespeichert: {}", dto.getTitle());

        redirectAttributes.addFlashAttribute("success", "TestCase erfolgreich gespeichert");
        return REDIRECT;
    }

    // ===== DELETE =====
    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes
    ) {

        testCaseService.deleteById(id);

        log.info("TestCase gelöscht: {}", id);

        redirectAttributes.addFlashAttribute("success", "TestCase gelöscht");
        return REDIRECT;
    }

    // ===== HELPER =====
    private void populateModel(Model model, TestCaseDTO dto) {

        model.addAttribute("testcase", dto);
        model.addAttribute("testcases", testCaseService.findAllDTOs());
        model.addAttribute("requirements", requirementService.findAllDTOs());
    }
}