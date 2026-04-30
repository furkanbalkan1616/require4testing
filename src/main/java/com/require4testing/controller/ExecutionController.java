package com.require4testing.controller;

import com.require4testing.dto.ExecutionDTO;
import com.require4testing.enums.ExecutionResult;
import com.require4testing.service.ExecutionService;
import com.require4testing.service.TestCaseService;
import com.require4testing.service.TestRunService;
import com.require4testing.service.TesterService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/executions")
public class ExecutionController {

    private static final Logger log = LoggerFactory.getLogger(ExecutionController.class);

    private static final String VIEW = "executions";
    private static final String REDIRECT = "redirect:/executions";

    private final ExecutionService service;
    private final TestCaseService testCaseService;
    private final TestRunService testRunService;
    private final TesterService testerService;

    public ExecutionController(
            ExecutionService service,
            TestCaseService testCaseService,
            TestRunService testRunService,
            TesterService testerService
    ) {
        this.service = service;
        this.testCaseService = testCaseService;
        this.testRunService = testRunService;
        this.testerService = testerService;
    }

    // ===== LIST =====
    @GetMapping
    public String list(Model model) {

        populateModel(model, new ExecutionDTO());

        model.addAttribute("activePage", "executions"); // 🔥 FIX

        log.info("Execution-Seite geladen");

        return VIEW;
    }

    // ===== SAVE =====
    @PostMapping
    public String save(
            @Valid @ModelAttribute("execution") ExecutionDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            log.warn("Validation Fehler beim Execution speichern");
            populateModel(model, dto);
            model.addAttribute("activePage", "executions"); // 🔥 FIX
            return VIEW;
        }

        service.save(dto);

        log.info("Execution gespeichert für TestCase ID: {}", dto.getTestCaseId());

        redirectAttributes.addFlashAttribute("success", "Execution erfolgreich gespeichert");
        return REDIRECT;
    }

    // ===== DELETE =====
    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
    ) {

        service.deleteById(id);

        log.info("Execution gelöscht: {}", id);

        redirectAttributes.addFlashAttribute("success", "Execution gelöscht");
        return REDIRECT;
    }

    // ===== HELPER =====
    private void populateModel(Model model, ExecutionDTO dto) {

        // Form
        model.addAttribute("execution", dto);

        // Data
        model.addAttribute("executions", service.findAllDTOs());
        model.addAttribute("testcases", testCaseService.findAllDTOs());
        model.addAttribute("testruns", testRunService.findAllDTOs());
        model.addAttribute("testers", testerService.findAll());

        // Enum
        model.addAttribute("results", ExecutionResult.values());

        // ===== DASHBOARD =====
        long total = service.count();
        long passed = service.countPassed();
        long failed = service.countFailed();

        model.addAttribute("totalExecutions", total);
        model.addAttribute("passedExecutions", passed);
        model.addAttribute("failedExecutions", failed);

        double successRate = (total > 0)
                ? (passed * 100.0) / total
                : 0;

        model.addAttribute("successRate", Math.round(successRate));
    }
}