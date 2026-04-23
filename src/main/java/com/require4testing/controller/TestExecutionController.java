package com.require4testing.controller;

import com.require4testing.entity.TestExecution;
import com.require4testing.service.TestExecutionService;
import com.require4testing.service.TestCaseService;
import com.require4testing.service.TestRunService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestExecutionController {

    private final TestExecutionService executionService;
    private final TestCaseService testCaseService;
    private final TestRunService testRunService;

    public TestExecutionController(TestExecutionService executionService,
                                   TestCaseService testCaseService,
                                   TestRunService testRunService) {
        this.executionService = executionService;
        this.testCaseService = testCaseService;
        this.testRunService = testRunService;
    }

    @GetMapping("/executions")
    public String getExecutions(Model model) {
        model.addAttribute("executions", executionService.findAll());
        model.addAttribute("testcases", testCaseService.findAll());
        model.addAttribute("testruns", testRunService.findAll());
        model.addAttribute("execution", new TestExecution());
        return "executions";
    }

    @PostMapping("/executions")
    public String saveExecution(@ModelAttribute TestExecution execution) {
        executionService.save(execution);
        return "redirect:/executions";
    }
}
