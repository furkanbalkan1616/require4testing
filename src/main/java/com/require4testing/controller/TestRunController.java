package com.require4testing.controller;

import com.require4testing.dto.TestRunDTO;
import com.require4testing.enums.Status;
import com.require4testing.service.TestCaseService;
import com.require4testing.service.TestRunService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/testruns")
public class TestRunController {

    private static final Logger log = LoggerFactory.getLogger(TestRunController.class);

    private static final String VIEW = "testruns";
    private static final String REDIRECT = "redirect:/testruns";

    private final TestRunService service;
    private final TestCaseService testCaseService;

    public TestRunController(
            TestRunService service,
            TestCaseService testCaseService
    ) {
        this.service = service;
        this.testCaseService = testCaseService;
    }

    // ===== LIST =====
    @GetMapping
    public String list(Model model) {

        populateModel(model, new TestRunDTO());

        model.addAttribute("activePage", "testruns"); // 🔥 FIX

        log.info("TestRun-Seite geladen");

        return VIEW;
    }

    // ===== SAVE =====
    @PostMapping
    public String save(
            @Valid @ModelAttribute("testrunDTO") TestRunDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            log.warn("Validation Fehler beim TestRun speichern");
            populateModel(model, dto);
            model.addAttribute("activePage", "testruns"); // 🔥 FIX
            return VIEW;
        }

        service.save(dto);

        log.info("TestRun gespeichert: {}", dto.getName());

        redirectAttributes.addFlashAttribute("success", "TestRun erfolgreich gespeichert");
        return REDIRECT;
    }

    // ===== DELETE =====
    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
    ) {

        service.deleteById(id);

        log.info("TestRun gelöscht: {}", id);

        redirectAttributes.addFlashAttribute("success", "TestRun gelöscht");
        return REDIRECT;
    }

    // ===== HELPER =====
    private void populateModel(Model model, TestRunDTO dto) {

        model.addAttribute("testrunDTO", dto);
        model.addAttribute("testruns", service.findAllDTOs());
        model.addAttribute("testcases", testCaseService.findAllDTOs());
        model.addAttribute("statuses", Status.values());
    }
}