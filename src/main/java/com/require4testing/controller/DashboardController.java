package com.require4testing.controller;

import com.require4testing.service.ExecutionService;
import com.require4testing.service.RequirementService;
import com.require4testing.service.TestCaseService;
import com.require4testing.service.TestRunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);

    private final ExecutionService executionService;
    private final RequirementService requirementService;
    private final TestCaseService testCaseService;
    private final TestRunService testRunService;

    public DashboardController(
            ExecutionService executionService,
            RequirementService requirementService,
            TestCaseService testCaseService,
            TestRunService testRunService
    ) {
        this.executionService = executionService;
        this.requirementService = requirementService;
        this.testCaseService = testCaseService;
        this.testRunService = testRunService;
    }

    // ===== ROOT → REDIRECT =====
    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    // ===== DASHBOARD =====
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        log.info("Dashboard geladen");

        // ===== KPI DATEN =====
        long totalExecutions = executionService.count();
        long passed = executionService.countPassed();
        long failed = executionService.countFailed();

        long blocked = Math.max(0, totalExecutions - passed - failed);

        long totalRequirements = requirementService.count();
        long totalTestCases = testCaseService.count();
        long totalTestRuns = testRunService.count();

        // ===== SUCCESS RATE =====
        long successRate = (totalExecutions > 0)
                ? Math.round((passed * 100.0) / totalExecutions)
                : 0;

        // ===== MODEL =====
        model.addAttribute("totalExecutions", totalExecutions);
        model.addAttribute("passed", passed);
        model.addAttribute("failed", failed);
        model.addAttribute("blocked", blocked);
        model.addAttribute("successRate", successRate);

        model.addAttribute("requirements", totalRequirements);
        model.addAttribute("testcases", totalTestCases);
        model.addAttribute("testruns", totalTestRuns);

        // ===== LETZTE 5 EXECUTIONS =====
        model.addAttribute(
                "executions",
                executionService.findAllDTOs()
                        .stream()
                        .limit(5)
                        .toList()
        );

        // 🔥 GANZ WICHTIG (für Navbar!)
        model.addAttribute("activePage", "dashboard");

        return "dashboard";
    }
}